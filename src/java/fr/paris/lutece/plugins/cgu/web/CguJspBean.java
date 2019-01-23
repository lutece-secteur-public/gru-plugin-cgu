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

import fr.paris.lutece.plugins.cgu.business.Cgu;
import fr.paris.lutece.plugins.cgu.business.CguHome;
import fr.paris.lutece.plugins.cgu.business.CguVersion;
import fr.paris.lutece.plugins.cgu.business.CguVersionHome;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * This class provides the user interface to manage Cgu features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageCgus.jsp", controllerPath = "jsp/admin/plugins/cgu/", right = "CGU_MANAGEMENT" )
public class CguJspBean extends AbstractManageJspBean
{
    /**
	 * 
	 */
    private static final long serialVersionUID = -7746974008322818952L;
    // Templates
    private static final String TEMPLATE_MANAGE_CGUS = "/admin/plugins/cgu/manage_cgus.html";
    private static final String TEMPLATE_CREATE_CGU = "/admin/plugins/cgu/create_cgu.html";
    private static final String TEMPLATE_MODIFY_CGU = "/admin/plugins/cgu/modify_cgu.html";

    // Parameters
    private static final String PARAMETER_ID_CGU = "id";
    private static final String PARAMETER_ID_CGU_VERSION = "id";
    private static final String PARAMETER_CODE = "cgu_code";
    private static final String PARAMETER_CSS = "css";
    private static final String PARAMETER_TEXT = "text";
    private static final String PARAMETER_MINIMUM_AGE = "minimum_age";

    private static final int ZERO = 0;

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_CGUS = "cgu.manage_cgus.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_CGU = "cgu.modify_cgu.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_CGU = "cgu.create_cgu.pageTitle";

    // Markers
    private static final String MARK_CGU_LIST = "cgu_list";
    private static final String MARK_CGU = "cgu";
    private static final String MARK_WEBAPP_URL = "webapp_url";

    private static final String JSP_MANAGE_CGUS = "jsp/admin/plugins/cgu/ManageCgus.jsp";

    // Validations
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "cgu.model.entity.cgu.attribute.";

    // Views
    private static final String VIEW_MANAGE_CGUS = "manageCgus";
    private static final String VIEW_CREATE_CGU = "createCgu";
    private static final String VIEW_MODIFY_CGU = "modifyCgu";

    // Actions
    private static final String ACTION_CREATE_CGU = "createCgu";
    private static final String ACTION_MODIFY_CGU = "modifyCgu";
    private static final String ACTION_PUBLISHED_CGU_VERSION = "publishedCguVersion";

    // Infos
    private static final String INFO_CGU_CREATED = "cgu.info.cgu.created";
    private static final String INFO_CGU_UPDATED = "cgu.info.cgu.updated";
    private static final String INFO_PUBLISHED_CGU_SUCCESSFUL = "cgu.info.cgu.cguCreated";
    private static final String INFO_CGU_CODE_ALREADY_USED = "cgu.info.cgu.cguCodeAlreadyUsed";
    private static final String WARNING_EMPTY_TEXT = "cgu.info.cgu.emptyText";
    private static final String WARNING_EMPTY_MINIMUM_AGE = "cgu.info.cgu.emptyMinimumAge";

    // Actions
    private static final String ACTION_CREATE = "create";
    private static final String ACTION_MODIFY = "modify";

    // Session variable to store working values
    private Cgu _cgu;

    /**
     * Build the Manage View
     * 
     * @param request
     *            The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_CGUS, defaultView = true )
    public String getManageCgus( HttpServletRequest request )
    {
        _cgu = null;
        List<Cgu> listCgu = CguHome.findCguList( );
        for ( Cgu cgu : listCgu )
        {
            List<CguVersion> listCguVersion = CguVersionHome.findCguVersionsByIdCguOrderedByIdCguAndVersion( cgu.getId( ) );
            cgu.setCguVersions( listCguVersion );
        }
        Map<String, Object> model = getPaginatedListModel( request, MARK_CGU_LIST, listCgu, JSP_MANAGE_CGUS );

        model.put( MARK_WEBAPP_URL, AppPathService.getBaseUrl( request ) );
        return getPage( PROPERTY_PAGE_TITLE_MANAGE_CGUS, TEMPLATE_MANAGE_CGUS, model );
    }

    /**
     * Returns the form to create a cgu
     *
     * @param request
     *            The Http request
     * @return the html code of the cgu form
     */
    @View( VIEW_CREATE_CGU )
    public String getCreateCgu( HttpServletRequest request )
    {
        _cgu = ( _cgu != null ) ? _cgu : new Cgu( );

        Map<String, Object> model = getModel( );
        model.put( MARK_CGU, _cgu );
        model.put( MARK_WEBAPP_URL, AppPathService.getBaseUrl( request ) );
        return getPage( PROPERTY_PAGE_TITLE_CREATE_CGU, TEMPLATE_CREATE_CGU, model );
    }

    /**
     * Process the data capture form of a new cgu
     *
     * @param request
     *            The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_CGU )
    public String doCreateCgu( HttpServletRequest request )
    {
        populate( _cgu, request, request.getLocale( ) );

        // Check constraints
        if ( !validateBean( _cgu, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_CGU );
        }

        if ( !validateCGU( request, ACTION_CREATE ) )
        {
            return redirectView( request, VIEW_CREATE_CGU );
        }

        String cguCode = request.getParameter( PARAMETER_CODE );
        _cgu.setCguCode( cguCode );
        _cgu.setCss( request.getParameter( PARAMETER_CSS ) );

        CguVersion cguVersion = new CguVersion( );
        CguHome.create( _cgu );
        cguVersion.setCguId( _cgu.getId( ) );
        cguVersion.setText( request.getParameter( PARAMETER_TEXT ) );
        if ( request.getParameter( PARAMETER_MINIMUM_AGE ) != null && !request.getParameter( PARAMETER_MINIMUM_AGE ).isEmpty( )
                && Integer.parseInt( request.getParameter( PARAMETER_MINIMUM_AGE ) ) > ZERO )
        {
            cguVersion.setMinimumAge( Integer.parseInt( request.getParameter( PARAMETER_MINIMUM_AGE ) ) );
        }
        else
        {
            cguVersion.setMinimumAge( ZERO );
        }
        CguVersionHome.create( cguVersion );
        addError( INFO_CGU_CREATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_CGUS );
    }

    /**
     * Returns the list cgu to publish version of cgu
     *
     * @param request
     *            The Http request
     * @return The HTML list cgu
     */
    @Action( ACTION_PUBLISHED_CGU_VERSION )
    public String doPublishedCguVersion( HttpServletRequest request )
    {
        int nIdCguVersion = Integer.parseInt( request.getParameter( PARAMETER_ID_CGU_VERSION ) );
        CguVersion cguVersion = CguVersionHome.findByPrimaryKey( nIdCguVersion );
        cguVersion.setPublished( true );
        CguVersionHome.update( cguVersion );
        doCreateNewCguVersion( cguVersion );
        return redirectView( request, VIEW_MANAGE_CGUS );
    }

    /**
     * Returns the list cgu to create a new version of cgu
     *
     * @param cguVersionOld
     *            the new cguVersionOld
     */
    private void doCreateNewCguVersion( CguVersion cguVersionOld )
    {
        CguVersion cguVersion = new CguVersion( );
        cguVersion.setCguId( cguVersionOld.getCguId( ) );
        cguVersion.setMinimumAge( cguVersionOld.getMinimumAge( ) );
        cguVersion.setText( cguVersionOld.getText( ) );
        cguVersion.setVersion( cguVersionOld.getVersion( ) + 1 );

        CguVersionHome.create( cguVersion );
        addInfo( INFO_PUBLISHED_CGU_SUCCESSFUL, getLocale( ) );
    }

    /**
     * Process the change form of a cgu
     *
     * @param request
     *            The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_CGU )
    public String doModifyCgu( HttpServletRequest request )
    {
        populate( _cgu, request, request.getLocale( ) );

        // Check constraints
        if ( !validateBean( _cgu, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_CGU, PARAMETER_ID_CGU, _cgu.getId( ) );
        }

        if ( !validateCGU( request, ACTION_MODIFY ) )
        {
            return redirect( request, VIEW_MODIFY_CGU, PARAMETER_ID_CGU, _cgu.getId( ) );
        }

        String strCguCode = request.getParameter( PARAMETER_CODE );
        _cgu.setCguCode( strCguCode );
        CguHome.update( _cgu );

        CguVersion cguVersion = CguVersionHome.findUnpublishedCguVersionByCode( strCguCode );
        if ( request.getParameter( PARAMETER_MINIMUM_AGE ) != null && !request.getParameter( PARAMETER_MINIMUM_AGE ).isEmpty( )
                && Integer.parseInt( request.getParameter( PARAMETER_MINIMUM_AGE ) ) > ZERO )
        {
            cguVersion.setMinimumAge( Integer.parseInt( request.getParameter( PARAMETER_MINIMUM_AGE ) ) );
        }
        else
        {
            cguVersion.setMinimumAge( ZERO );
        }
        cguVersion.setText( request.getParameter( PARAMETER_TEXT ) );

        CguVersionHome.update( cguVersion );
        addInfo( INFO_CGU_UPDATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_CGUS );
    }

    /**
     * Returns the form to update info about a cgu
     *
     * @param request
     *            The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_CGU )
    public String getModifyCgu( HttpServletRequest request )
    {
        int nIdCgu = Integer.parseInt( request.getParameter( PARAMETER_ID_CGU ) );

        if ( _cgu == null || ( _cgu.getId( ) != nIdCgu ) )
        {
            _cgu = CguHome.findByPrimaryKey( nIdCgu );
            List<CguVersion> listCguVersion = CguVersionHome.findCguVersionsByIdCgu( _cgu.getId( ) );
            _cgu.setCguVersions( listCguVersion );
        }

        Map<String, Object> model = getModel( );
        model.put( MARK_CGU, _cgu );
        model.put( MARK_WEBAPP_URL, AppPathService.getBaseUrl( request ) );
        return getPage( PROPERTY_PAGE_TITLE_MODIFY_CGU, TEMPLATE_MODIFY_CGU, model );
    }

    /**
     * 
     * check if code is not already use / text is not empty and minimum age is not empty return true if ok else adderror message corresponding to what's wrong
     * 
     * @param request
     *            the HttpServletRequest request
     * @param action
     *            action made the get in this method
     * @return false if cgu is not valid
     */
    private boolean validateCGU( HttpServletRequest request, String action )
    {
        boolean isValid = true;
        _cgu.setCss( request.getParameter( PARAMETER_CSS ) );
        Cgu cgu = CguHome.findByCode( request.getParameter( PARAMETER_CODE ) );
        if ( ( action.equalsIgnoreCase( ACTION_CREATE ) && cgu != null )
                || ( action.equalsIgnoreCase( ACTION_MODIFY ) && cgu != null && cgu.getId( ) != _cgu.getId( ) ) )
        {
            addError( INFO_CGU_CODE_ALREADY_USED, getLocale( ) );
            isValid = false;
        }
        _cgu.setCguCode( request.getParameter( PARAMETER_CODE ) );

        CguVersion cguVersion = new CguVersion( );
        List<CguVersion> listCguVersion = new ArrayList<CguVersion>( );
        if ( request.getParameter( PARAMETER_MINIMUM_AGE ) == null || request.getParameter( PARAMETER_MINIMUM_AGE ).isEmpty( ) )
        {
            addError( WARNING_EMPTY_MINIMUM_AGE, getLocale( ) );
            isValid = false;
        }
        else
        {
            cguVersion.setMinimumAge( Integer.parseInt( request.getParameter( PARAMETER_MINIMUM_AGE ) ) );
        }
        if ( request.getParameter( PARAMETER_TEXT ) == null || request.getParameter( PARAMETER_TEXT ).isEmpty( ) )
        {
            addError( WARNING_EMPTY_TEXT, getLocale( ) );
            isValid = false;
        }
        else
        {
            cguVersion.setText( request.getParameter( PARAMETER_TEXT ) );
        }
        listCguVersion.add( cguVersion );
        _cgu.setCguVersions( listCguVersion );
        return isValid;
    }
}
