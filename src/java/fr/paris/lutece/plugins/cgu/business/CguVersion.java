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

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * This is the business class for the object CguVersion
 */
public class CguVersion implements Serializable
{
    private static final long serialVersionUID = 1L;

    // Variables declarations
    private int _nId;
    private int _nCguId;
    @NotEmpty( message = "#i18n{cgu.validation.cgu.Texte.notEmpty}" )
    private String _strText;
    private int _nVersion;
    private boolean _bPublished;
    private int _nMinimumAge;

    /**
     * cguVersion constructor
     */
    public CguVersion( )
    {
        super( );
        _nVersion = 1;
        _bPublished = false;
    }

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
        this._nId = nId;
    }

    /**
     * @return the _nCguId
     */
    public int getCguId( )
    {
        return _nCguId;
    }

    /**
     * @param nCguId
     *            the _nCguId to set
     */
    public void setCguId( int nCguId )
    {
        this._nCguId = nCguId;
    }

    /**
     * Returns the Text
     * 
     * @return The Text
     */
    public String getText( )
    {
        return _strText;
    }

    /**
     * Sets the Text
     * 
     * @param strText
     *            The Text
     */
    public void setText( String strText )
    {
        _strText = strText;
    }

    /**
     * Returns the Version
     * 
     * @return The Version
     */
    public int getVersion( )
    {
        return _nVersion;
    }

    /**
     * Sets the Version
     * 
     * @param strVersion
     *            The Version
     */
    public void setVersion( int strVersion )
    {
        _nVersion = strVersion;
    }

    /**
     * check if the CGU is published.
     * 
     * @return true if the cGU is published
     */
    public boolean isPublished( )
    {
        return _bPublished;
    }

    /**
     * set true if the CGU is published
     * 
     * @param bPublished
     *            true if the CGU is published
     */
    public void setPublished( boolean bPublished )
    {
        this._bPublished = bPublished;
    }

    /**
     * @return the minimumAge
     */
    public int getMinimumAge( )
    {
        return _nMinimumAge;
    }

    /**
     * @param nMinimumAge
     *            the minimumAge to set
     */
    public void setMinimumAge( int nMinimumAge )
    {
        this._nMinimumAge = nMinimumAge;
    }
}
