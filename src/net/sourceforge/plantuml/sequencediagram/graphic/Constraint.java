/* ========================================================================
 * Plantuml : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009, Arnaud Roques (for Atos Origin).
 *
 * Project Info:  http://plantuml.sourceforge.net
 * 
 * This file is part of Plantuml.
 *
 * Plantuml is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Plantuml distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * Original Author:  Arnaud Roques (for Atos Origin).
 *
 */
package net.sourceforge.plantuml.sequencediagram.graphic;

class Constraint {

	private final Pushable p1;
	private final Pushable p2;
	private double value;

	public Constraint(Pushable p1, Pushable p2) {
		if (p1 == null || p2 == null) {
			throw new IllegalArgumentException();
		}
		this.p1 = p1;
		this.p2 = p2;
	}

	public final Pushable getParticipant1() {
		return p1;
	}

	public final Pushable getParticipant2() {
		return p2;
	}

	public final double getValue() {
		return value;
	}

	public final void ensureValue(double newValue) {
		if (newValue > value) {
			this.value = newValue;
		}
	}

	public void push(double delta) {
		value += delta;
	}

}
