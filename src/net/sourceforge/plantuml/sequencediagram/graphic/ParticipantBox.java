/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009, Arnaud Roques (for Atos Origin).
 *
 * Project Info:  http://plantuml.sourceforge.net
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
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

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.SimpleContext2D;

class ParticipantBox implements Pushable {

	private double startingX;
	private double alignY;

	private final Component head;
	private final Component line;
	private final Component tail;

	public ParticipantBox(Component head, Component line, Component tail, double startingX) {
		this.startingX = startingX;
		this.head = head;
		this.line = line;
		this.tail = tail;
	}

	public double getMaxX(Graphics2D g2d) {
		return startingX + head.getPreferredWidth(g2d);
	}

	public double getCenterX(Graphics2D g2d) {
		return startingX + head.getPreferredWidth(g2d) / 2.0;
	}

	public double getHeadHeight(Graphics2D g2d) {
		return head.getPreferredHeight(g2d) + line.getPreferredHeight(g2d) / 2.0;
	}

	public double getTailHeight(Graphics2D g2d) {
		return tail.getPreferredHeight(g2d) + line.getPreferredHeight(g2d) / 2.0;
	}

	public void pushToLeft(double deltaX) {
		startingX += deltaX;
	}

	public void setAlignY(double alignY) {
		this.alignY = alignY;
	}

	public double getAlignY() {
		return alignY;
	}

	public void drawHeadTail(Graphics2D g2d, Dimension2D dimensionToUse, double deltaY) {
		if (alignY == 0) {
			throw new IllegalStateException("setAlignY not called");
		}

		final AffineTransform t = g2d.getTransform();

		final double y1 = alignY + deltaY - head.getPreferredHeight(g2d) - line.getPreferredHeight(g2d) / 2;
		g2d.translate(startingX, y1 - t.getTranslateY());
		head.draw(g2d, new Dimension2DDouble(head.getPreferredWidth(g2d), head.getPreferredHeight(g2d)),
				new SimpleContext2D(false));

		g2d.setTransform(t);

		final double y2 = dimensionToUse.getHeight() + line.getPreferredHeight(g2d) / 2 - alignY - 1;
		g2d.translate(startingX, y2 - t.getTranslateY());
		tail.draw(g2d, new Dimension2DDouble(tail.getPreferredWidth(g2d), tail.getPreferredHeight(g2d)),
				new SimpleContext2D(false));

		g2d.setTransform(t);
	}

	public void drawLine(Graphics2D g2d, Dimension2D dimensionToUse, double deltaY) {
		if (alignY == 0) {
			throw new IllegalStateException("setAlignY not called");
		}
		final AffineTransform t = g2d.getTransform();

		final double yStart = alignY + deltaY - line.getPreferredHeight(g2d) / 2;
		// final double yEnd = dimensionToUse.getHeight() +
		// line.getPreferredHeight(g2d) / 2 - getTailHeight(g2d) - 1;
		final double yEnd = dimensionToUse.getHeight() + line.getPreferredHeight(g2d) / 2 - alignY - 1;

		g2d.translate(startingX, yStart);
		line.draw(g2d, new Dimension2DDouble(head.getPreferredWidth(g2d), yEnd - yStart), new SimpleContext2D(false));

		g2d.setTransform(t);

	}

}
