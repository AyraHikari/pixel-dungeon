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
package com.watabou.pixeldungeon.actors.hero;

import com.watabou.utils.Bundle;

public enum HeroSubClass {

	NONE( null, null ),
	
	GLADIATOR( "budak", 
		"Serangan yang sukses dengan senjata jarak dekat memungkinkan _budak_ untuk memulai kombo, " +
		"di mana setiap hit yang sukses berikutnya menimbulkan lebih banyak kerusakan." ),
	BERSERKER( "pengamuk", 
		"Ketika terluka parah, _Pengamuk_ memasuki kondisi amarah liar " +
		"secara signifikan meningkatkan output kerusakannya." ),
	
	WARLOCK( "ahli sihir", 
		"Setelah membunuh musuh, _Ahli sihir_ menghabiskan jiwanya. " +
		"Itu menyembuhkan luka-lukanya dan memuaskan rasa laparnya." ),
	BATTLEMAGE( "penyihir tempur", 
		"Saat bertarung dengan tongkat di tangannya, _Penyihir tempur_ menimbulkan kerusakan tambahan tergantung " +
		"pada jumlah kerusakan saat ini. Setiap hit yang berhasil mengembalikan 1 biaya untuk tongkat ini." ),
	
	ASSASSIN( "pembunuh", 
		"Saat melakukan serangan mendadak, _Pembunuh_ menimbulkan kerusakan tambahan pada targetnya." ),
	FREERUNNER( "pelari bebas", 
		"_Pelari bebas_ dapat bergerak hampir dua kali lebih cepat, daripada sebagian besar monster. Kapan dia " +
		"sedang berjalan, Pelari bebas jauh lebih sulit untuk dipukul. Untuk itu ia harus tidak terbebani dan tidak kelaparan." ),
		
	SNIPER( "penembak jitu", 
		"_Penembak jitu_ dapat mendeteksi titik lemah dalam baju besi musuh, " +
		"secara efektif mengabaikannya saat menggunakan senjata rudal." ),
	WARDEN( "sipir", 
		"Memiliki hubungan yang kuat dengan kekuatan alam memberi _Sipir_ kemampuan untuk mengumpulkan tetesan embun dan " +
		"biji dari tanaman. Juga menginjak-injak rumput tinggi memberi mereka ekstra buff sementara untuk baju besi." );
	
	private String title;
	private String desc;
	
	private HeroSubClass( String title, String desc ) {
		this.title = title;
		this.desc = desc;
	}
	
	public String title() {
		return title;
	}
	
	public String desc() {
		return desc;
	}
	
	private static final String SUBCLASS	= "subClass";
	
	public void storeInBundle( Bundle bundle ) {
		bundle.put( SUBCLASS, toString() );
	}
	
	public static HeroSubClass restoreInBundle( Bundle bundle ) {
		String value = bundle.getString( SUBCLASS );
		try {
			return valueOf( value );
		} catch (Exception e) {
			return NONE;
		}
	}
	
}
