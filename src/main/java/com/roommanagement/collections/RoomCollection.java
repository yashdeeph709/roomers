package com.roommanagement.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.roommanagement.beans.Room;

@Document(collection="rooms")
public class RoomCollection {
	
	@Id
	private String id;
	private String roomName;
	private String roomCity;
	private String roomLocation;
	private String roomBlock;
	private String roomAddress;
	private int roomCapacity;
	private int roomTables;
	private int roomMachines;
	private int roomBoard;
	private int roomChart;
	private int roomScreen;
	private int roomProjector;
	private boolean roomInternet;
	
	
		public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


	public String getRoomCity() {
		return roomCity;
	}


	public void setRoomCity(String roomCity) {
		this.roomCity = roomCity;
	}


	public String getRoomLocation() {
		return roomLocation;
	}


	public void setRoomLocation(String roomLocation) {
		this.roomLocation = roomLocation;
	}


	public String getRoomBlock() {
		return roomBlock;
	}


	public void setRoomBlock(String roomBlock) {
		this.roomBlock = roomBlock;
	}


	public String getRoomAddress() {
		return roomAddress;
	}


	public void setRoomAddress(String roomAddress) {
		this.roomAddress = roomAddress;
	}


	public int getRoomCapacity() {
		return roomCapacity;
	}


	public void setRoomCapacity(int roomCapacity) {
		this.roomCapacity = roomCapacity;
	}


	public int getRoomTables() {
		return roomTables;
	}


	public void setRoomTables(int roomTables) {
		this.roomTables = roomTables;
	}


	public int getRoomMachines() {
		return roomMachines;
	}


	public void setRoomMachines(int roomMachines) {
		this.roomMachines = roomMachines;
	}


	public int getRoomBoard() {
		return roomBoard;
	}


	public void setRoomBoard(int roomBoard) {
		this.roomBoard = roomBoard;
	}


	public int getRoomChart() {
		return roomChart;
	}


	public void setRoomChart(int roomChart) {
		this.roomChart = roomChart;
	}


	public int getRoomScreen() {
		return roomScreen;
	}


	public void setRoomScreen(int roomScreen) {
		this.roomScreen = roomScreen;
	}


	public int getRoomProjector() {
		return roomProjector;
	}


	public void setRoomProjector(int roomProjector) {
		this.roomProjector = roomProjector;
	}


	public boolean getRoomInternet() {
		return roomInternet;
	}


	public void setRoomInternet(boolean roomInternet) {
		this.roomInternet = roomInternet;
	}


	public RoomCollection() {
		
	}


	public RoomCollection(Room room) {
		super();
		
		this.roomName = room.getRoomName();
		this.roomCity = room.getRoomCity();
		this.roomLocation = room.getRoomLocation();
		this.roomBlock = room.getRoomBlock();
		this.roomAddress = room.getRoomAddress();
		this.roomCapacity = room.getRoomCapacity();
		this.roomTables = room.getRoomTables();
		this.roomMachines = room.getRoomMachines();
		this.roomBoard = room.getRoomBoard();
		this.roomChart = room.getRoomChart();
		this.roomScreen = room.getRoomScreen();
		this.roomProjector = room.getRoomProjector();
		this.roomInternet = room.getRoomInternet();
	}


	@Override
	public String toString() {
		return "RoomCollection [id=" + id + ", roomName=" + roomName + ", roomCity=" + roomCity + ", roomLocation="
				+ roomLocation + ", roomBlock=" + roomBlock + ", roomAddress=" + roomAddress + ", roomCapacity="
				+ roomCapacity + ", roomTables=" + roomTables + ", roomMachines=" + roomMachines + ", roomBoard="
				+ roomBoard + ", roomChart=" + roomChart + ", roomScreen=" + roomScreen + ", roomProjector="
				+ roomProjector + ", roomInternet=" + roomInternet + "]";
	}

	

}
