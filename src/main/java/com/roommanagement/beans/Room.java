package com.roommanagement.beans;

public class Room {
	
	private String roomName;
	private String roomCity;
	private String roomLocation;
	private String roomBlock;
	private String roomAddress;
	private String roomCapacity;
	private String roomTables;
	private String roomMachines;
	private String roomBoard;
	private String roomChart;
	private String roomScreen;
	private String roomProjector;
	private String roomInternet;
	
	
	
	
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
	public String getRoomCapacity() {
		return roomCapacity;
	}
	public void setRoomCapacity(String roomCapacity) {
		this.roomCapacity = roomCapacity;
	}
	public String getRoomTables() {
		return roomTables;
	}
	public void setRoomTables(String roomTables) {
		this.roomTables = roomTables;
	}
	public String getRoomMachines() {
		return roomMachines;
	}
	public void setRoomMachines(String roomMachines) {
		this.roomMachines = roomMachines;
	}
	public String getRoomBoard() {
		return roomBoard;
	}
	public void setRoomBoard(String roomBoard) {
		this.roomBoard = roomBoard;
	}
	public String getRoomChart() {
		return roomChart;
	}
	public void setRoomChart(String roomChart) {
		this.roomChart = roomChart;
	}
	public String getRoomScreen() {
		return roomScreen;
	}
	public void setRoomScreen(String roomScreen) {
		this.roomScreen = roomScreen;
	}
	public String getRoomProjector() {
		return roomProjector;
	}
	public void setRoomProjector(String roomProjector) {
		this.roomProjector = roomProjector;
	}
	public String getRoomInternet() {
		return roomInternet;
	}
	public void setRoomInternet(String roomInternet) {
		this.roomInternet = roomInternet;
	}
	@Override
	public String toString() {
		return "Room [roomName=" + roomName + ", roomCity=" + roomCity + ", roomLocation=" + roomLocation
				+ ", roomBlock=" + roomBlock + ", roomAddress=" + roomAddress + ", roomCapacity=" + roomCapacity
				+ ", roomTables=" + roomTables + ", roomMachines=" + roomMachines + ", roomBoard=" + roomBoard
				+ ", roomChart=" + roomChart + ", roomScreen=" + roomScreen + ", roomProjector=" + roomProjector
				+ ", roomInternet=" + roomInternet + "]";
	}
	public Room(String roomName, String roomCity, String roomLocation, String roomBlock, String roomAddress,
			String roomCapacity, String roomTables, String roomMachines, String roomBoard, String roomChart,
			String roomScreen, String roomProjector, String roomInternet) {
		super();
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
	
	public Room() {}

}
