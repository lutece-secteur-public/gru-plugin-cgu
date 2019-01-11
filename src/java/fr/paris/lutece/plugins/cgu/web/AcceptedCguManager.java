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
package fr.paris.lutece.plugins.cgu.web;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.cgu.business.AcceptedCgu;
import fr.paris.lutece.plugins.cgu.business.CguVersion;
import fr.paris.lutece.plugins.cgu.exception.CguNotAcceptedException;
import fr.paris.lutece.plugins.cgu.exception.MinimumAgeNotAcceptedException;
import fr.paris.lutece.plugins.cgu.service.CguService;
import fr.paris.lutece.plugins.cgu.service.ICguService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.html.HtmlTemplate;

/**
 * 
 * manage methods for outside purpose
 *
 */
public class AcceptedCguManager
{

    private static final String TEMPLATE_ACCEPT_CGU = "skin/plugins/cgu/accept_cgu.html";
    private static final String PARAMETER_IS_MINIMUM_AGE = "is_minimum_age";
    private static final String PARAMETER_IS_ACCEPTED_CGU = "is_accepted_cgu";
    private static final String MARKER_CGU_CODE = "cgu_code";
    private static final String MARKER_CGU_VERSION = "cgu_version";

    private static final String MESSAGE_MANDATORY_MINIMUM_AGE = "message.mandatory.isMinimumAgeRequired";
    private static final String MESSAGE_MANDATORY_ACCEPT_CGU = "message.mandatory.acceptedCGURequired";

    private static CguService _cguService = SpringContextService.getBean( ICguService.BEAN_NAME );

    /**
     * create a line accepted cgu
     * 
     * @param request the HttpServletRequest request
     * @param strCguCode the cguCode
     * @param nIdResource the idResource
     * @param strResourceType the resourceType
     * @return the accepted cgu
     * @throws CguNotAcceptedException if cgu not accepted
     * @throws MinimumAgeNotAcceptedException if minimum age not certified
     */
    public AcceptedCgu acceptCgu( HttpServletRequest request, String strCguCode, int nIdResource, String strResourceType ) throws CguNotAcceptedException,
            MinimumAgeNotAcceptedException
    {
        if ( request.getParameter( PARAMETER_IS_MINIMUM_AGE ) == null )
        {
            throw new MinimumAgeNotAcceptedException( MESSAGE_MANDATORY_MINIMUM_AGE );
        }
        if ( request.getParameter( PARAMETER_IS_ACCEPTED_CGU ) == null )
        {
            throw new CguNotAcceptedException( MESSAGE_MANDATORY_ACCEPT_CGU );
        }

        return _cguService.acceptCgu( strCguCode, nIdResource, strResourceType );
    }

    /**
     * get the html accept cgu
     * 
     * @param strCguCode the cguCode
     * @return the template accept_cgu
     */
    public static String getHtmlToDisplay( String strCguCode )
    {
        CguVersion cguVersion = _cguService.findLastVersion( strCguCode );

        HashMap<String, Object> model = new HashMap<String, Object>( );
        model.put( MARKER_CGU_CODE, strCguCode );
        model.put( MARKER_CGU_VERSION, cguVersion );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_ACCEPT_CGU, Locale.getDefault( ), model );

        return template.toString( );
    }
}
