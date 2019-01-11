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

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for Cgu objects
 */
public final class CguDAO implements ICguDAO
{
    // Constants
    private static final String SQL_QUERY_SELECT = "SELECT id_cgu, cgu_code, description, css FROM cgu_cgu WHERE id_cgu = ?";
    private static final String SQL_QUERY_SELECT_BY_CGU_CODE = "SELECT id_cgu, cgu_code, description, css FROM cgu_cgu WHERE cgu_code = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO cgu_cgu ( cgu_code, description, css ) VALUES ( ?, ?, ?) ";
    private static final String SQL_QUERY_UPDATE = "UPDATE cgu_cgu SET cgu_code = ?, id_cgu = ?, description = ?, css = ? WHERE id_cgu = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_cgu, cgu_code, description FROM cgu_cgu";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_cgu FROM cgu_cgu";

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( Cgu cgu, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, Statement.RETURN_GENERATED_KEYS, plugin );
        try
        {
            int nIndex = 1;
            daoUtil.setString( nIndex++, cgu.getCguCode( ) );
            daoUtil.setString( nIndex++, cgu.getDescription( ) );
            daoUtil.setString( nIndex++, cgu.getCss( ) );
            daoUtil.executeUpdate( );
            if ( daoUtil.nextGeneratedKey( ) )
            {
                cgu.setId( daoUtil.getGeneratedKeyInt( 1 ) );
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
    public Cgu load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery( );
        Cgu cgu = null;

        if ( daoUtil.next( ) )
        {
            cgu = new Cgu( );
            int nIndex = 1;

            cgu.setId( daoUtil.getInt( nIndex++ ) );
            cgu.setCguCode( daoUtil.getString( nIndex++ ) );
            cgu.setDescription( daoUtil.getString( nIndex++ ) );
            cgu.setCss( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.close( );
        return cgu;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( Cgu cgu, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
        int nIndex = 1;

        daoUtil.setString( nIndex++, cgu.getCguCode( ) );
        daoUtil.setInt( nIndex++, cgu.getId( ) );
        daoUtil.setString( nIndex++, cgu.getDescription( ) );
        daoUtil.setString( nIndex++, cgu.getCss( ) );
        daoUtil.setInt( nIndex, cgu.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.close( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Cgu> selectCguList( Plugin plugin )
    {
        List<Cgu> cguList = new ArrayList<Cgu>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            Cgu cgu = new Cgu( );
            int nIndex = 1;

            cgu.setId( daoUtil.getInt( nIndex++ ) );
            cgu.setCguCode( daoUtil.getString( nIndex++ ) );
            cgu.setDescription( daoUtil.getString( nIndex++ ) );
            cguList.add( cgu );
        }

        daoUtil.close( );
        return cguList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdCguList( Plugin plugin )
    {
        List<Integer> cguList = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            cguList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.close( );
        return cguList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectCguReferenceList( Plugin plugin )
    {
        ReferenceList cguList = new ReferenceList( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            cguList.addItem( daoUtil.getInt( 1 ), daoUtil.getString( 2 ) );
        }

        daoUtil.close( );
        return cguList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Cgu selectByCode( String strCguCode, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_CGU_CODE, plugin );
        daoUtil.setString( 1, strCguCode );
        daoUtil.executeQuery( );
        Cgu cgu = null;

        if ( daoUtil.next( ) )
        {
            cgu = new Cgu( );
            int nIndex = 1;

            cgu.setId( daoUtil.getInt( nIndex++ ) );
            cgu.setCguCode( daoUtil.getString( nIndex++ ) );
            cgu.setDescription( daoUtil.getString( nIndex++ ) );
            cgu.setCss( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.close( );
        return cgu;
    }
}
