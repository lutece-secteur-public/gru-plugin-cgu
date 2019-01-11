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

/**
 * This is the business class for the object AcceptedCgu
 */
public class AcceptedCgu
{

    private int _nIdResource;
    private String _strResourceType;
    private int _nIdCguVersion;

    /**
     * @return the idResource
     */
    public int getIdResource( )
    {
        return _nIdResource;
    }

    /**
     * @param nIdResource
     *            the idResource to set
     */
    public void setIdResource( int nIdResource )
    {
        this._nIdResource = nIdResource;
    }

    /**
     * @return the _strResourceType
     */
    public String getResourceType( )
    {
        return _strResourceType;
    }

    /**
     * @param strResourceType
     *            the resourceType to set
     */
    public void setResourceType( String strResourceType )
    {
        this._strResourceType = strResourceType;
    }

    /**
     * @return the idCguVersion
     */
    public int getIdCguVersion( )
    {
        return _nIdCguVersion;
    }

    /**
     * @param nIdCguVersion
     *            the idCguVersion to set
     */
    public void setIdCguVersion( int nIdCguVersion )
    {
        this._nIdCguVersion = nIdCguVersion;
    }

}
