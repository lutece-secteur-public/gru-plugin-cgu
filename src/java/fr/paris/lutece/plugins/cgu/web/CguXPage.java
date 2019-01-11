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

import fr.paris.lutece.plugins.cgu.business.CguHome;
import fr.paris.lutece.plugins.cgu.business.CguVersion;
import fr.paris.lutece.plugins.cgu.business.CguVersionHome;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.portal.util.mvc.xpage.MVCApplication;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.xpage.annotations.Controller;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * This class provides the user interface to manage Cgu xpages ( manage, create, modify, remove )
 */
@Controller( xpageName = "cgu", pageTitleI18nKey = "cgu.xpage.cgu.pageTitle", pagePathI18nKey = "cgu.xpage.cgu.pagePathLabel" )
public class CguXPage extends MVCApplication
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 3058843249974089823L;
    // Templates
    private static final String TEMPLATE_VIEW_CGU = "/skin/plugins/cgu/view_cgu.html";

    // Parameters
    private static final String PARAMETER_CGU_CODE = "cgu_code";
    private static final String PARAMETER_CGU_VERSION = "cgu_version";

    // Markers
    private static final String MARK_CGU_VERSION = "cguVersion";
    private static final String MARK_CGU_CSS = "css";

    // Message
    // private static final String MESSAGE_CONFIRM_REMOVE_CGU = "cgu.message.confirmRemoveCgu";

    // Views
    private static final String VIEW_PREVIEW_CGU = "preview_cgu";
    private static final String VIEW_CGU = "cgu";

    /**
     * return the form to manage cgus
     * 
     * @param request
     *            The Http request
     * @return the html code of the list of cgus
     */
    @View( value = VIEW_CGU, defaultView = true )
    public XPage getCgu( HttpServletRequest request )
    {
        String strCguCode = request.getParameter( PARAMETER_CGU_CODE );
        Map<String, Object> model = getModel( );
        model.put( MARK_CGU_VERSION, CguVersionHome.findLastVersionPublishedByCguCode( strCguCode ) );
        model.put( MARK_CGU_CSS, CguHome.findByCode( strCguCode ).getCss( ) );
        return getXPage( TEMPLATE_VIEW_CGU, request.getLocale( ), model );
    }

    /**
     * return the form to manage cgus
     * 
     * @param request
     *            The Http request
     * @return the html code of the list of cgus
     */
    @View( value = VIEW_PREVIEW_CGU )
    public XPage getPreviewCgu( HttpServletRequest request )
    {
        String strCguCode = request.getParameter( PARAMETER_CGU_CODE );
        int nCguVersion = Integer.parseInt( request.getParameter( PARAMETER_CGU_VERSION ) );
        Map<String, Object> model = getModel( );
        CguVersion cguVersion = CguVersionHome.findByCodeAndVersion( strCguCode, nCguVersion );

        model.put( MARK_CGU_VERSION, cguVersion );
        model.put( MARK_CGU_CSS, CguHome.findByCode( strCguCode ).getCss( ) );

        return getXPage( TEMPLATE_VIEW_CGU, request.getLocale( ), model );
    }

}
