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

import org.hibernate.validator.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * This is the business class for the object Cgu
 */
public class Cgu implements Serializable
{
    private static final long serialVersionUID = 1L;

    // Variables declarations
    private int _nId;
    private String _strCguCode;
    @NotEmpty( message = "#i18n{cgu.validation.cgu.Description.notEmpty}" )
    private String _strDescription;
    private List<CguVersion> _listCguVersion;
    private String _strCss;

    /**
     * Returns the Id
     * 
     * @return The Id
     */
    public int getId( )
    {
        return _nId;
    }

    /**
     * Sets the Id
     * 
     * @param nId
     *            The Id
     */
    public void setId( int nId )
    {
        _nId = nId;
    }

    /**
     * Returns the Description
     * 
     * @return The Description
     */
    public String getDescription( )
    {
        return _strDescription;
    }

    /**
     * Sets the Description
     * 
     * @param strDescription
     *            The Description
     */
    public void setDescription( String strDescription )
    {
        _strDescription = strDescription;
    }

    /**
     *
     * @return a list of version can be use for the cgu
     */
    public List<CguVersion> getCguVersions( )
    {
        return _listCguVersion;
    }

    /**
     * set a list of version can be use for the cgu
     * 
     * @param listCguVersion
     *            a list of version must be use for the cgu
     */
    public void setCguVersions( List<CguVersion> listCguVersion )
    {
        _listCguVersion = listCguVersion;
    }

    /**
     * 
     * @return cguCode
     */
    public String getCguCode( )
    {
        return _strCguCode;
    }

    /**
     * 
     * @param strCguCode the cguCode to set
     */
    public void setCguCode( String strCguCode )
    {
        this._strCguCode = strCguCode;
    }

    /**
     * @return the css
     */
    public String getCss( )
    {
        return _strCss;
    }

    /**
     * @param strCss
     *            the css to set
     */
    public void setCss( String strCss )
    {
        this._strCss = strCss;
    }
}
