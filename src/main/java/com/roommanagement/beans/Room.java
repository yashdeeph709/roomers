package com.roommanagement.beans;

import com.roommanagement.collections.RoomCollection;

public class Room {

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

	@Override
	public String toString() {
		return "Room [id=" + id + ", roomName=" + roomName + ", roomCity=" + roomCity + ", roomLocation=" + roomLocation
				+ ", roomBlock=" + roomBlock + ", roomAddress=" + roomAddress + ", roomCapacity=" + roomCapacity
				+ ", roomTables=" + roomTables + ", roomMachines=" + roomMachines + ", roomBoard=" + roomBoard
				+ ", roomChart=" + roomChart + ", roomScreen=" + roomScreen + ", roomProjector=" + roomProjector
				+ ", roomInternet=" + roomInternet + "]";
	}

	public Room() {

	}

	public Room(RoomCollection roomCollection) {

		this.id = roomCollection.getId();
		this.roomName = roomCollection.getRoomName();
		this.roomCity = roomCollection.getRoomCity();
		this.roomLocation = roomCollection.getRoomLocation();
		this.roomBlock = roomCollection.getRoomBlock();
		this.roomAddress = roomCollection.getRoomAddress();
		this.roomCapacity = roomCollection.getRoomCapacity();
		this.roomTables = roomCollection.getRoomTables();
		this.roomMachines = roomCollection.getRoomMachines();
		this.roomBoard = roomCollection.getRoomBoard();
		this.roomChart = roomCollection.getRoomChart();
		this.roomScreen = roomCollection.getRoomScreen();
		this.roomProjector = roomCollection.getRoomProjector();
		this.roomInternet = roomCollection.getRoomInternet();

	}

	public Room(String id, String roomName, String roomCity, String roomLocation, String roomBlock, String roomAddress,
			int roomCapacity, int roomTables, int roomMachines, int roomBoard, int roomChart, int roomScreen,
			int roomProjector, boolean roomInternet) {

		super();
		this.id = id;
		this.roomName = roomName;
		this.roomCity = roomCity;
		this.roomLocation = roomLocation;
		this.roomBlock = roomBlock;
		this.roomAddress = roomAddress;
		this.roomCapacity = roomCapacity;
		this.roomTables = roomTables;
		this.roomMachines = roomMachines;
		this.roomBoard = roomBoard;
		this.roomChart = roomChart;
		this.roomScreen = roomScreen;
		this.roomProjector = roomProjector;
		this.roomInternet = roomInternet;
	}

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

}
