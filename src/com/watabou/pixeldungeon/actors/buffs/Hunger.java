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
package com.watabou.pixeldungeon.actors.buffs;

import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.ResultDescriptions;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.hero.HeroClass;
import com.watabou.pixeldungeon.items.rings.RingOfSatiety;
import com.watabou.pixeldungeon.ui.BuffIndicator;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.pixeldungeon.utils.Utils;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Hunger extends Buff implements Hero.Doom {

	private static final float STEP	= 10f;
	
	public static final float HUNGRY	= 260f;
	public static final float STARVING	= 360f;
	
	private static final String TXT_HUNGRY		= "Kamu lapar.";
	private static final String TXT_STARVING	= "Kamu kelaparan!";
	private static final String TXT_DEATH		= "Anda kelaparan sampai mati...";
	
	private float level;

	private static final String LEVEL	= "level";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( LEVEL, level );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		level = bundle.getFloat( LEVEL );
	}
	
	@Override
	public boolean act() {
		if (target.isAlive()) {
			
			Hero hero = (Hero)target;
			
			if (isStarving()) {
				if (Random.Float() < 0.3f && (target.HP > 1 || !target.paralysed)) {
					
					GLog.n( TXT_STARVING );
					hero.damage( 1, this );
					
					hero.interrupt();
				}
			} else {	
				
				int bonus = 0;
				for (Buff buff : target.buffs( RingOfSatiety.Satiety.class )) {
					bonus += ((RingOfSatiety.Satiety)buff).level;
				}
				
				float newLevel = level + STEP - bonus;
				boolean statusUpdated = false;
				if (newLevel >= STARVING) {
					
					GLog.n( TXT_STARVING );
					statusUpdated = true;
					
					hero.interrupt();
					
				} else if (newLevel >= HUNGRY && level < HUNGRY) {
					
					GLog.w( TXT_HUNGRY );
					statusUpdated = true;
					
				}
				level = newLevel;
				
				if (statusUpdated) {
					BuffIndicator.refreshHero();
				}
				
			}
			
			float step = ((Hero)target).heroClass == HeroClass.ROGUE ? STEP * 1.2f : STEP;
			spend( target.buff( Shadows.class ) == null ? step : step * 1.5f );
			
		} else {
			
			diactivate();
			
		}

		return true;
	}
	
	public void satisfy( float energy ) {
		level -= energy;
		if (level < 0) {
			level = 0;
		} else if (level > STARVING) {
			level = STARVING;
		}
		
		BuffIndicator.refreshHero();
	}
	
	public boolean isStarving() {
		return level >= STARVING;
	}
	
	@Override
	public int icon() {
		if (level < HUNGRY) {
			return BuffIndicator.NONE;
		} else if (level < STARVING) {
			return BuffIndicator.HUNGER;
		} else {
			return BuffIndicator.STARVATION;
		}
	}
	
	@Override
	public String toString() {
		if (level < STARVING) {
			return "Lapar";
		} else {
			return "Kelaparan";
		}
	}

	@Override
	public void onDeath() {
		
		Badges.validateDeathFromHunger();
		
		Dungeon.fail( Utils.format( ResultDescriptions.HUNGER, Dungeon.depth ) );
		GLog.n( TXT_DEATH );
	}
}
