/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon.scenes;

import android.content.Intent;
import android.net.Uri;

import com.watabou.input.Touchscreen.Touch;
import com.watabou.noosa.BitmapTextMultiline;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.TouchArea;
import com.watabou.pixeldungeon.PixelDungeon;
import com.watabou.pixeldungeon.effects.Flare;
import com.watabou.pixeldungeon.ui.Archs;
import com.watabou.pixeldungeon.ui.ExitButton;
import com.watabou.pixeldungeon.ui.Icons;
import com.watabou.pixeldungeon.ui.Window;

public class AboutScene extends PixelScene {

	private static final String TXT = 
		"Kode & grafik: Watabou\n" +
		"Musik: Cube_Code\n" + 
		"Penerjemah: Ayra Hikari (Tedy Ramdhani)\n\n" + 
		"Game ini terinspirasi oleh Brian Walker's Brogue. " +
		"Cobalah di Windows, Mac OS atau Linux - ini luar biasa! ;)\n\n" +
		"Jika ada masalah atau belum di terjemmah, kontak saya:";
	
	private static final String LNK = "t.me/AyraHikari";
	
	@Override
	public void create() {
		super.create();
		
		BitmapTextMultiline text = createMultiline( TXT, 8 );
		text.maxWidth = Math.min( Camera.main.width, 120 );
		text.measure();
		add( text );
		
		text.x = align( (Camera.main.width - text.width()) / 2 );
		text.y = align( (Camera.main.height - text.height()) / 2 );
		
		BitmapTextMultiline link = createMultiline( LNK, 8 );
		link.maxWidth = Math.min( Camera.main.width, 120 );
		link.measure();
		link.hardlight( Window.TITLE_COLOR );
		add( link );
		
		link.x = text.x;
		link.y = text.y + text.height();
		
		TouchArea hotArea = new TouchArea( link ) {
			@Override
			protected void onClick( Touch touch ) {
				Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "http://" + LNK ) );
				Game.instance.startActivity( intent );
			}
		};
		add( hotArea );
		
		Image wata = Icons.WATA.get();
		wata.x = align( (Camera.main.width - wata.width) / 2 );
		wata.y = text.y - wata.height - 8;
		add( wata );
		
		new Flare( 7, 64 ).color( 0x112233, true ).show( wata, 0 ).angularSpeed = +20;
		
		Archs archs = new Archs();
		archs.setSize( Camera.main.width, Camera.main.height );
		addToBack( archs );
		
		ExitButton btnExit = new ExitButton();
		btnExit.setPos( Camera.main.width - btnExit.width(), 0 );
		add( btnExit );
		
		fadeIn();
	}
	
	@Override
	protected void onBackPressed() {
		PixelDungeon.switchNoFade( TitleScene.class );
	}
}
