

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;


public class GameState extends State{

	private World world;
	private Player player;

	private Car[] cars;
	private int[] carPos;
	private Car car;

	private Truck[] trucks;
	private int[] truckPos;
	private Truck truck;

	private Shark shark;
	private Shark[] sharks;
	private int[] sharkPos;

	private Log[] logs;
	private int[] logPos;
	private Log log;
	private Log log2;

	private LaserDot laserdot;

	private int gameHeight;

	private int level;

	public GameState(Handler handler){
		super(handler); // getting it from the state class

		world = new World(handler, "WorldsToUse/WorldB.txt");
		int gameHeight = world.getHeight() * 2;
		player = new Player(handler, 150, 280, "Player");

		cars = new Car[gameHeight];
		carPos = world.getRoadPosition();

		trucks = new Truck[gameHeight];
		truckPos = world.getRoadPosition();

		sharks = new Shark[gameHeight];
		sharkPos = world.getWaterPosition();

		logs = new Log[gameHeight];
		logPos = world.getWaterPosition();

		laserdot = new LaserDot(handler, 0, 0 , "LaserDot");

		level = 1;
	
		
		for(int i = 0; i< carPos.length; i++ ){
			if (carPos[i] != 0 ){
				car = new Car(handler, 0, carPos[i]* 50, "Car");
				cars[i] = car;

			}else{
				cars[i] = null;
			}
		}

		for(int i = 0; i< truckPos.length; i++ ){
			if (truckPos[i] != 0 ){
				truck = new Truck(handler, -100, truckPos[i]* 50 + 25, "Truck");
				trucks[i] = truck;

			}else{
				trucks[i] = null;
			}
		}

		for(int i = 0; i< sharkPos.length; i++ ){
			if (sharkPos[i] != 0 ){
				shark = new Shark(handler, 300, sharkPos[i]* 50, "Shark");
				sharks[i] = shark;

			}else{
				sharks[i] = null;
			}
		}

		for(int i = 0; i< logPos.length; i++){
			if (logPos[i] != 0 ){
				log = new Log(handler, 100, logPos[i]* 50, "Log1");
				logs[i] = log;
				// log2 = new Log(handler, 50, logPos[i] * 50 + 25, "Log2");// (50+ 25), "Log");
				// logs[i+1] = log2;
			}else if (logs[i] != null){

			}else{
				logs[i] = null;
			}
		}

		handler.setWorld(world);
		
	}

	public void tick(){


		laserdot.tick();
		world.tick();
		player.tick();

		for(int b = 0; b < cars.length; b++){
			if(cars[b] != null){
				cars[b].tick();
			}
		}

		for(int b = 0; b < sharks.length; b++){
			if(sharks[b] != null){
				sharks[b].tick();
			}
		}

		for(int b = 0; b < logs.length; b++){
			if(logs[b] != null){
				logs[b].tick();
			}
		}

		for(int b = 0; b < trucks.length; b++){
			if(trucks[b] != null){
				trucks[b].tick();
			}
		}
		
	}

	public void render(Graphics g){

		world.render(g);

		for(int b = 0; b < cars.length; b++){
			if(cars[b] != null){
				cars[b].render(g);
			}
		}

		for(int b = 0; b < sharks.length; b++){
			if(sharks[b] != null){
				sharks[b].render(g);
			}
		}

		for(int b = 0; b < logs.length; b++){
			if(logs[b] != null){
				logs[b].render(g);
			}
		}

		for(int b = 0; b < trucks.length; b++){
			if(trucks[b] != null){
				trucks[b].render(g);
			}
		}

		laserdot.render(g);

		player.render(g);
		
	}

	public Player getPlayer(){
		return player;
	}


	public World getWorld(){
		return world;
	}

	public int getNumLogs(){
		return 2;
	}
	// public void levelUp(){
	// 	level = level + 1;
	// 	if(level == 2){
	// 		world = new World(handler, "WorldsToUse/WorldA.txt");
	// 	}else if(level == 3){
	// 		world = new World(handler, "WorldsToUse/WorldC.txt");
	// 	}else if(level == 4){
	// 		System.out.println("You win FINally");
	// 	}
		
	// }


}