package com.gc.action;

public class SkyDogImpl implements SkyDogSkill {

	@Override
	public void castSkill(String name) {
		System.out.println("技能施放中...");
		awake(name);
	}

	@Override
	public void awake(String name) {
		System.out.println("觉醒中...");
	}
	
	public void getStatus(String status){
		System.out.println("SSR....");
	}

}
