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
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/* FAIT PAR ROMAINPC */


public class Main extends Application {
	
	
	//coordonnées personnage (coin haut gauche)
	//attention ce sont des ratios !
	private static SimpleDoubleProperty HGX = new SimpleDoubleProperty();
	private static SimpleDoubleProperty HGY = new SimpleDoubleProperty();
	private static double vitesseY = 0;
	
	private static ImageView personnage;
	
	
	//touches:
	private static boolean droite = false;
	private static boolean gauche  = false;
	
	private static boolean saut  = false;
	private static int TIMERSAUTVALUE = 23;
	private static double VITESSESAUT = 0.022d;
	private static int timerSaut;
	
	private static double g = 0.002d;
	private static double vMarche = 0.01d;

	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			//groupe principal:
			
			Group root = new Group();
			Scene scene = new Scene(root,1280,720);
			
			
			
			//décor:
			// --> "sol" à 20% de la hauteur de la fenêtre (en partant du bas)
			ImageView background = new ImageView(new Image("background.png"));
			Affichage.configBackground(background, scene, root);
			
			
			
			
			
			//personnage:
			//résolution par défaut : 60*120 (soit 4.6875 %  *  16.6666666 %)
			personnage = new ImageView(new Image("troll.png"));
			
			HGX.set(0.1d);
			HGY.set(0.3d);
			
			Affichage.configurer(personnage, 0.046875d, 0.166666d, HGX, HGY);
			
			timerSaut = TIMERSAUTVALUE;
			
			
			
			
			//boucle de jeu:
			
			AnimationTimer boucle = new AnimationTimer() {
				@Override
				public void handle(long arg0) {
					
					
					
					
					
					
					
					//gravité:
						if(personnage.getLayoutY() + personnage.getFitHeight() <= background.getFitHeight() * 0.8d){
							HGY.set(HGY.get()  + vitesseY);
							vitesseY += g; 
						}
					
						if(personnage.getLayoutY() + personnage.getFitHeight() > background.getFitHeight() * 0.8d){
							HGY.set((background.getFitHeight() * 0.8d - personnage.getFitHeight()) / background.getFitHeight());
							vitesseY = 0;
						}
					
					
					//directions:
					if(droite) {
						HGX.set(HGX.get() + vMarche);
					}
					
					if(gauche) {
						HGX.set(HGX.get() - vMarche);
					}
					
					
					if(saut) {
						
						
						
						HGY.set(HGY.get() + vitesseY);
						
						
						timerSaut--;
						
						
						if(timerSaut <= 0) {
							saut = false;
						}
					}
					
					
					/*
					Circle c = new Circle(20);
					c.setLayoutX(personnage.getLayoutX());
					c.setLayoutY(personnage.getLayoutY());
					root.getChildren().addAll(c);
					
					*/
					
					
					
				}
				
			};
			boucle.start();
			
			
			
			
			
			
			
			
			// actions clavier:
			scene.setOnKeyPressed(e -> {
				switch(e.getCode()){
				
				case LEFT :
					gauche = true;
					break;
				case RIGHT :
					droite = true;
					break;
				
				default:
					break;
					
				}
				
			});
			
			scene.setOnKeyReleased(e -> {
				switch(e.getCode()){
				
				case LEFT :
					gauche = false;
					break;
				case RIGHT :
					droite = false;
					break;
					
				case SPACE :
					if(!saut) {
						saut = true;
						timerSaut = TIMERSAUTVALUE;
						vitesseY = - VITESSESAUT;
					}
					break;
				
				default:
					break;
					
				}
				
			});
			
			
			
			
			
			
			//gestion fenêtre:
			root.getChildren().addAll(background, personnage);
			
			
			
			
			scene.setFill(Color.BLACK);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
