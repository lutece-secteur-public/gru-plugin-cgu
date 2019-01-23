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

import java.util.List;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

/**
 * This class provides instances management methods (create, find, ...) for CguVersion objects
 */
public final class CguVersionHome
{

    // Static variable pointed at the DAO instance
    private static ICguVersionDAO _dao = SpringContextService.getBean( "cgu.cguVersionDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "cgu" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private CguVersionHome( )
    {
    }

    /**
     * Create an instance of the cguVersion class
     * 
     * @param cguVersion
     *            The instance of the cguVersion which contains the informations to store
     * @return The instance of cguVersion which has been created with its primary key.
     */
    public static CguVersion create( CguVersion cguVersion )
    {
        _dao.insert( cguVersion, _plugin );

        return cguVersion;
    }

    /**
     * load the data of all cguVersion objects by idCgu and returns as a list
     * 
     * @param nIdCgu
     *            id cgu
     * @return the list which contains the data of all the cguVersoin
     */

    public static List<CguVersion> findCguVersionsByIdCgu( int nIdCgu )
    {
        return _dao.selectCguVersionsByIdCgu( nIdCgu );
    }

    /**
     * load the data of all cguVersion objects ordered by id, version DESC by idCgu and returns as a list
     * 
     * @param nIdCgu
     *            id cgu
     * @return the list which contains the data of all the cguVersoin ordered by id, version DESC
     */

    public static List<CguVersion> findCguVersionsByIdCguOrderedByIdCguAndVersion( int nIdCgu )
    {
        return _dao.selectCguVersionsByIdCguOrderedByIdCguAndVersion( nIdCgu );
    }

    /**
     * Update of the cguVersion which is specified in parameter
     * 
     * @param cguVersion
     *            The instance of the cguVersion which contains the data to store
     * @return The instance of the cguVersion which has been updated
     */
    public static CguVersion update( CguVersion cguVersion )
    {
        _dao.store( cguVersion, _plugin );

        return cguVersion;
    }

    /**
     * get the last version published by cgu code
     * 
     * @param strCguCode
     *            the cguCode
     * @return the cguVersion
     */
    public static CguVersion findLastVersionPublishedByCguCode( String strCguCode )
    {
        return _dao.selectLastVersionPublishedByCguCode( strCguCode, _plugin );
    }

    /**
     * get the cguVersion corresponding to the id version and cgu code
     * 
     * @param strCguCode
     *            the cguCode
     * @param nVersion
     *            the number of version
     * @return the cguVersion corresponding
     */
    public static CguVersion findByCodeAndVersion( String strCguCode, int nVersion )
    {
        return _dao.selectByCodeAndVersion( strCguCode, nVersion, _plugin );
    }

    /**
     * get the cguVersion corresponding to the id version
     * 
     * @param nIdVersion
     *            the id cguVersion
     * @return the cguVersion corresponding to the id
     */
    public static CguVersion findByPrimaryKey( int nIdVersion )
    {
        return _dao.load( nIdVersion, _plugin );
    }

    /**
     * get the cguVersion corresponding to cguCode
     * 
     * @param strCguCode
     *            the cguCode
     * @return the cguVersion corresponding
     */
    public static CguVersion findUnpublishedCguVersionByCode( String strCguCode )
    {
        return _dao.selectUnpublishedCguVersionByCguCode( strCguCode, _plugin );
    }
}
