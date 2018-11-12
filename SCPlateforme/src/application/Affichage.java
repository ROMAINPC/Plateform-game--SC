/*******************************************************************************
 * Copyright (C) 2018 ROMAINPC_LECHAT
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package application;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;


/* FAIT PAR ROMAINPC */



public class Affichage {
	
	private static double RATIO = 16d/9d;
	private static ImageView background;
	
	
	
	public static void configBackground(ImageView iV, Scene s, Group g) {
		
		background = iV;
		
		When condition = Bindings.when((s.widthProperty().divide(s.heightProperty())).greaterThanOrEqualTo(RATIO));
		iV.fitWidthProperty().bind(condition.then(iV.fitHeightProperty().multiply(RATIO)).otherwise(s.widthProperty()));
		iV.fitHeightProperty().bind(condition.then(s.heightProperty()).otherwise(iV.fitWidthProperty().divide(RATIO)));
		
		g.layoutXProperty().bind((s.widthProperty().subtract(iV.fitWidthProperty())).divide(2));
		g.layoutYProperty().bind((s.heightProperty().subtract(iV.fitHeightProperty())).divide(2));
		
		
	}



	public static void configurer(ImageView iV, double LRatio, double HRatio, SimpleDoubleProperty hGX, SimpleDoubleProperty hGY) {
		
		iV.fitHeightProperty().bind(background.fitHeightProperty().multiply(HRatio));
		iV.fitWidthProperty().bind(background.fitWidthProperty().multiply(LRatio));
		
		iV.layoutXProperty().bind(background.fitWidthProperty().multiply(hGX));
		iV.layoutYProperty().bind(background.fitHeightProperty().multiply(hGY));
		
		
		
	}




	
}
