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
import fr.paris.lutece.plugins.cgu.business.CguVersion;

/**
 * 
 * ICguService interface
 *
 */
public interface ICguService
{

    String BEAN_NAME = "cgu.cguService";

    /**
     * Get last version of a CGU
     * 
     * @param strCguCode the cguCode
     * @return last version of the cgu
     */
    CguVersion findLastVersion( String strCguCode );

    /**
     * Save an accepted CGU
     * 
     * @param strCguCode the cguCode
     * @param nIdResource the idResource
     * @param strResourceType the resourceType
     * @return acceptedCgu
     */
    AcceptedCgu acceptCgu( String strCguCode, int nIdResource, String strResourceType );

    /**
     * Return true if an accepted CGU is the last version of a CGU
     * 
     * @param strCguCode the cguCode
     * @param nIdResource the idResource
     * @param strResourceType the resourceType
     * @return true if this is the last cgu version
     */
    boolean hasAcceptedLastVersion( String strCguCode, int nIdResource, String strResourceType );

}
