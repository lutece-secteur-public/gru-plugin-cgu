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

/**
 * ICguVersionDAO Interface
 */
public interface ICguVersionDAO
{

    /**
     * Insert a new record in the table.
     * 
     * @param cguVersion
     *            instance of the CguVersion object to insert
     * @param plugin
     *            the Plugin
     */
    void insert( CguVersion cguVersion, Plugin plugin );

    /**
     * Update the record in the table
     * 
     * @param cguVersion
     *            the reference of the CguVersion
     * @param plugin
     *            the Plugin
     */
    void store( CguVersion cguVersion, Plugin plugin );

    // /////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Load the data from the table
     * 
     * @param nKey
     *            The identifier of the cguVersion
     * @param plugin
     *            the Plugin
     * @return The instance of the cguVersion
     */
    CguVersion load( int nKey, Plugin plugin );

    /**
     * list the version of cgu by id_cgu
     * 
     * @param nIdCgu
     *            id of cgu
     * @return the list of version a cgu
     */
    List<CguVersion> selectCguVersionsByIdCgu( int nIdCgu );

    /**
     * list the version of cgu by id_cgu ordererd by id_cgu and version DESC
     * 
     * @param nIdCgu
     *            id of cgu
     * @return the list of version a cgu ordered by version DESC
     */
    List<CguVersion> selectCguVersionsByIdCguOrderedByIdCguAndVersion( int nIdCgu );

    /**
     * get the last cgu version published
     * 
     * @param strCguCode
     *            the cguCode
     * @param plugin
     *            the plugin
     * @return the last cgu version published
     */
    CguVersion selectLastVersionPublishedByCguCode( String strCguCode, Plugin plugin );

    /**
     * get the cgu version by code and version
     * 
     * @param strCguCode
     *            the cguCode
     * @param nVersion
     *            the number of version
     * @param plugin
     *            the plugin
     * @return cguVersion
     */
    CguVersion selectByCodeAndVersion( String strCguCode, int nVersion, Plugin plugin );

    /**
     * get the unpublished cguversion by code
     * 
     * @param strCguCode
     *            the cguCode
     * @param plugin
     *            the plugin
     * @return cguVersion
     */
    CguVersion selectUnpublishedCguVersionByCguCode( String strCguCode, Plugin plugin );
}
