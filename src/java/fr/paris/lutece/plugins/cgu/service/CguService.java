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
package fr.paris.lutece.plugins.cgu.service;

import fr.paris.lutece.plugins.cgu.business.AcceptedCgu;
import fr.paris.lutece.plugins.cgu.business.AcceptedCguHome;
import fr.paris.lutece.plugins.cgu.business.CguVersion;
import fr.paris.lutece.plugins.cgu.business.CguVersionHome;

/**
 * 
 *  This class provides instances management methods (create, find, ...) for CguService objects
 *
 */
public class CguService implements ICguService
{

    /**
     * {@inheritDoc }
     */
    public CguVersion findLastVersion( String cguCode )
    {
        return CguVersionHome.findLastVersionPublishedByCguCode( cguCode );
    }

    /**
     * {@inheritDoc }
     */
    public AcceptedCgu acceptCgu( String strCguCode, int nIdResource, String strResourceType )
    {

        CguVersion lastCguVersion = CguVersionHome.findLastVersionPublishedByCguCode( strCguCode );

        AcceptedCgu acceptedCgu = new AcceptedCgu( );
        acceptedCgu.setIdResource( nIdResource );
        acceptedCgu.setResourceType( strResourceType );
        acceptedCgu.setIdCguVersion( lastCguVersion.getId( ) );

        return AcceptedCguHome.create( acceptedCgu );
    }

    /**
     * {@inheritDoc }
     */
    public boolean hasAcceptedLastVersion( String strCguCode, int nIdResource, String strResourceType )
    {
        CguVersion lastCguVersion = CguVersionHome.findLastVersionPublishedByCguCode( strCguCode );
        AcceptedCgu acceptedCgu = AcceptedCguHome.findByCodeAndResource( strCguCode, nIdResource, strResourceType );
        CguVersion cguVersionAccepted = CguVersionHome.findByPrimaryKey( acceptedCgu.getIdCguVersion( ) );

        return lastCguVersion.getVersion( ) == cguVersionAccepted.getVersion( );
    }

}
