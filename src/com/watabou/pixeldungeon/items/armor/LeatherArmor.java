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
package com.watabou.pixeldungeon.items.armor;

import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;


public class LeatherArmor extends Armor {

	{	
		name = "baju besi kulit";
		image = ItemSpriteSheet.ARMOR_LEATHER;
	}
	
	public LeatherArmor() {
		super( 2 );
	}
	
	@Override
	public String desc() {
		return "Armor yang terbuat dari kulit monster yang disamak. Tidak seringan baju besi kain tetapi memberikan perlindungan yang lebih baik.";
	}
}
