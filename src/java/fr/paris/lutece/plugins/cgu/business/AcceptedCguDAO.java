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
import fr.paris.lutece.util.sql.DAOUtil;

/**
 * This class provides Data Access methods for AcceptedCgu objects
 */
public class AcceptedCguDAO implements IAcceptedCguDAO
{

    private static final String SQL_QUERY_INSERT = "INSERT INTO cgu_accepted_cgu ( id_resource, resource_type, cgu_code, id_version, last_version_accepted, minimum_age_accepted ) VALUES ( ?, ?, ?, ?, ?, ?) ";
    private static final String SQL_QUERY_SELECT_BY_CODE_AND_RESOURCE = "SELECT id_resource, resource_type FROM cgu_accepted_cgu WHERE id_resource = ? AND resource_type = ?";

    /**
     * {@inheritDoc }
     */
    @Override
    public AcceptedCgu insert( AcceptedCgu acceptedCgu, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );
        try
        {
            int nIndex = 1;
            daoUtil.setInt( nIndex++, acceptedCgu.getIdResource( ) );
            daoUtil.setString( nIndex++, acceptedCgu.getResourceType( ) );

            daoUtil.executeUpdate( );
            return acceptedCgu;
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
    public AcceptedCgu selectByCodeAndResource( String strCguCode, int nIdResource, String strResourceType )
    {
        AcceptedCgu acceptedCgu = null;
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_CODE_AND_RESOURCE );
        daoUtil.setInt( 1, nIdResource );
        daoUtil.setString( 2, strResourceType );
        daoUtil.executeQuery( );

        if ( daoUtil.next( ) )
        {
            acceptedCgu = new AcceptedCgu( );
            int nIndex = 1;
            acceptedCgu.setIdResource( daoUtil.getInt( nIndex++ ) );
            acceptedCgu.setResourceType( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.close( );
        return acceptedCgu;
    }

}
