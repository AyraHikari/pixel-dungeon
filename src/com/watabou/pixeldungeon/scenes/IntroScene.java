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

import com.watabou.noosa.Game;
import com.watabou.pixeldungeon.windows.WndStory;

public class IntroScene extends PixelScene {

	private static final String TEXT = 	
		"Banyak pahlawan dari segala jenis berkelana ke Ruang bawah tanah sebelum Anda. Beberapa dari mereka telah kembali " +
		"dengan harta dan sihir artefak, sebagian besar belum pernah terdengar sejak itu. Tetapi tidak ada yang berhasil " +
		"mengambil Amulet Yendor, yang diceritakan disembunyikan di kedalaman Dungeon.\n\n" +
		"" +
		"Anda menganggap diri Anda siap untuk tantangan, tetapi yang paling penting, Anda merasa bahwa keberuntungan " +
		"tersenyum pada Anda. Saatnya memulai petualangan Anda sendiri!";
	
	@Override
	public void create() {
		super.create();
		
		add( new WndStory( TEXT ) {
			@Override
			public void hide() {
				super.hide();
				Game.switchScene( InterlevelScene.class );
			}
		} );
		
		fadeIn();
	}
}
