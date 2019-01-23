/*
 * Copyright (c) 2002-2019, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.cgu.business;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

/**
 * This class provides Data Access methods for CguVersion objects
 */
public final class CguVersionDAO implements ICguVersionDAO
{

    // Constants
    private static final String SQL_QUERY_SELECT = "SELECT id_cgu_version, id_cgu, version, text, is_published, minimum_age FROM cgu_version WHERE id_cgu = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO cgu_version ( id_cgu, version, text, is_published, minimum_age ) VALUES ( ?, ?, ?, ?, ?) ";
    private static final String SQL_QUERY_UPDATE = "UPDATE cgu_version SET id_cgu_version = ?, id_cgu = ?, version = ? , text = ? , is_published = ?, minimum_age = ? WHERE id_cgu_version = ?";
    private static final String SQL_ORDER_BY_VERSION = " ORDER BY version";
    private static final String SQL_QUERY_SELECT_BY_CGU_CODE_LAST_PUBLISHED = "SELECT id_cgu_version, cgu_cgu.id_cgu, version, text, is_published, minimum_age FROM cgu_version INNER JOIN cgu_cgu ON cgu_cgu.id_cgu = cgu_version.id_cgu WHERE cgu_cgu.cgu_code = ? AND is_published = true";
    private static final String SQL_QUERY_SELECT_BY_CGU_CODE_AND_VERSION = "SELECT id_cgu_version, cgu_version.id_cgu, version, text, is_published, minimum_age FROM cgu_version INNER JOIN cgu_cgu ON cgu_cgu.id_cgu = cgu_version.id_cgu WHERE cgu_cgu.cgu_code = ? AND version = ?";
    private static final String SQL_ORDER_BY_ID_CGU_AND_VERSION = " ORDER BY id_cgu, version DESC";
    private static final String SQL_QUERY_SELECT_BY_ID_CGU_VERSION = "SELECT id_cgu_version, id_cgu, version, text, is_published, minimum_age FROM cgu_version WHERE id_cgu_version = ?";
    private static final String SQL_QUERY_SELECT_UNPUBLISHED_BY_CGU_CODE = "SELECT id_cgu_version, cgu_version.id_cgu, version, text, is_published, minimum_age FROM cgu_version INNER JOIN cgu_cgu on cgu_cgu.id_cgu = cgu_version.id_cgu WHERE cgu_code = ? AND is_published = false";

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( CguVersion cguVersion, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, Statement.RETURN_GENERATED_KEYS, plugin );
        try
        {
            int nIndex = 1;
            daoUtil.setInt( nIndex++, cguVersion.getCguId( ) );
            daoUtil.setInt( nIndex++, cguVersion.getVersion( ) );
            daoUtil.setString( nIndex++, cguVersion.getText( ) );
            daoUtil.setBoolean( nIndex++, cguVersion.isPublished( ) );
            daoUtil.setInt( nIndex++, cguVersion.getMinimumAge( ) );

            daoUtil.executeUpdate( );
            if ( daoUtil.nextGeneratedKey( ) )
            {
                cguVersion.setId( daoUtil.getGeneratedKeyInt( 1 ) );
            }
        }
        finally
        {
            daoUtil.close( );
        }

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<CguVersion> selectCguVersionsByIdCgu( int nIdCgu )
    {
        List<CguVersion> cguVersionList = new ArrayList<CguVersion>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT + SQL_ORDER_BY_VERSION );
        daoUtil.setInt( 1, nIdCgu );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            cguVersionList.add( dataToObject( daoUtil ) );
        }

        daoUtil.close( );
        return cguVersionList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<CguVersion> selectCguVersionsByIdCguOrderedByIdCguAndVersion( int nIdCgu )
    {
        List<CguVersion> cguVersionList = new ArrayList<CguVersion>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT + SQL_ORDER_BY_ID_CGU_AND_VERSION );
        daoUtil.setInt( 1, nIdCgu );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            cguVersionList.add( dataToObject( daoUtil ) );
        }

        daoUtil.close( );
        return cguVersionList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( CguVersion cguVersion, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
        int nIndex = 1;

        daoUtil.setInt( nIndex++, cguVersion.getId( ) );
        daoUtil.setInt( nIndex++, cguVersion.getCguId( ) );
        daoUtil.setInt( nIndex++, cguVersion.getVersion( ) );
        daoUtil.setString( nIndex++, cguVersion.getText( ) );
        daoUtil.setBoolean( nIndex++, cguVersion.isPublished( ) );
        daoUtil.setInt( nIndex++, cguVersion.getMinimumAge( ) );
        daoUtil.setInt( nIndex++, cguVersion.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.close( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public CguVersion load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_ID_CGU_VERSION, plugin );

        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery( );
        CguVersion cguVersion = null;

        if ( daoUtil.next( ) )
        {
            cguVersion = dataToObject( daoUtil );
        }

        daoUtil.close( );

        return cguVersion;
    }

    /**
     * 
     * @param daoUtil
     *            The daoutil
     * @return The populated Form object
     *
     */
    private CguVersion dataToObject( DAOUtil daoUtil )
    {
        CguVersion cguVersion = new CguVersion( );
        cguVersion.setId( daoUtil.getInt( "id_cgu_version" ) );
        cguVersion.setCguId( daoUtil.getInt( "id_cgu" ) );
        cguVersion.setVersion( daoUtil.getInt( "version" ) );
        cguVersion.setText( daoUtil.getString( "text" ) );
        cguVersion.setPublished( daoUtil.getBoolean( "is_published" ) );
        cguVersion.setMinimumAge( daoUtil.getInt( "minimum_age" ) );
        return cguVersion;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public CguVersion selectLastVersionPublishedByCguCode( String strCguCode, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_CGU_CODE_LAST_PUBLISHED + SQL_ORDER_BY_ID_CGU_AND_VERSION, plugin );

        daoUtil.setString( 1, strCguCode );
        daoUtil.executeQuery( );
        CguVersion cguVersion = null;

        if ( daoUtil.next( ) )
        {
            cguVersion = dataToObject( daoUtil );
        }

        daoUtil.close( );

        return cguVersion;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public CguVersion selectByCodeAndVersion( String strCguCode, int nVersion, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_CGU_CODE_AND_VERSION, plugin );

        daoUtil.setString( 1, strCguCode );
        daoUtil.setInt( 2, nVersion );
        daoUtil.executeQuery( );
        CguVersion cguVersion = null;

        if ( daoUtil.next( ) )
        {
            cguVersion = dataToObject( daoUtil );
        }

        daoUtil.close( );

        return cguVersion;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public CguVersion selectUnpublishedCguVersionByCguCode( String strCguCode, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_UNPUBLISHED_BY_CGU_CODE, plugin );

        daoUtil.setString( 1, strCguCode );
        daoUtil.executeQuery( );
        CguVersion cguVersion = null;

        if ( daoUtil.next( ) )
        {
            cguVersion = dataToObject( daoUtil );
        }

        daoUtil.close( );

        return cguVersion;
    }
}
