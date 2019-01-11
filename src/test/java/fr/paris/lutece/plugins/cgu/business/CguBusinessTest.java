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

import fr.paris.lutece.test.LuteceTestCase;

/**
 * This is the business class test for the object Cgu
 */
public class CguBusinessTest extends LuteceTestCase
{
    private static final String DESCRIPTION1 = "Description1";
    private static final String DESCRIPTION2 = "Description2";
    private static final String VERSION1 = "Version1";
    private static final String VERSION2 = "Version2";
    private static final String TEXT1 = "Text1";
    private static final String TEXT2 = "Text2";
    private static final String CODE_CGU = "CodeCgu";

    /**
     * test Cgu
     */
    public void testBusiness( )
    {
        // Initialize an object
        Cgu cgu = new Cgu( );
        cgu.setDescription( DESCRIPTION1 );
        cgu.setCguCode( CODE_CGU );
        /*
         * cgu.setVersion( VERSION1 ); cgu.setText( TEXT1 );
         */

        // Create test
        CguHome.create( cgu );
        Cgu cguStored = CguHome.findByPrimaryKey( cgu.getId( ) );
        assertEquals( cguStored.getDescription( ), cgu.getDescription( ) );
        /*
         * assertEquals( cguStored.getVersion() , cgu.getVersion( ) ); assertEquals( cguStored.getText() , cgu.getText( ) );
         */
        // Update test
        cgu.setDescription( DESCRIPTION2 );
        /*
         * cgu.setVersion( VERSION2 ); cgu.setText( TEXT2 );
         */
        CguHome.update( cgu );
        cguStored = CguHome.findByPrimaryKey( cgu.getId( ) );
        assertEquals( cguStored.getDescription( ), cgu.getDescription( ) );
        /*
         * assertEquals( cguStored.getVersion() , cgu.getVersion( ) ); assertEquals( cguStored.getText() , cgu.getText( ) );
         */
        // List test
        CguHome.findCguList( );

    }

}
