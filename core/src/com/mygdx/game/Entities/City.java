package com.mygdx.game.Entities;

import java.util.ArrayList;

public class City {
    private Position position;
    private Building[] building = new Building[World.numberOfBuildings];
    private ArrayList<Plant> plant = new ArrayList<Plant>();
    private int population;
    private int infrastructure;
    private int owner;
    private int distance;
    private int prosperity;
    private int autonomy;
    private int profit;
    private int tax;
    private int religion;
    private int rebelLevel;

    private int numberOfModificators = 0;
    private Modificator[] modificator = new Modificator[numberOfModificators];

    public void Prosperity(){
        int i = (int) (Math.random() * 100);
        if (i < 10 - prosperity){
            prosperity++;
        }
    }

    public boolean ExchangeReligion(int prob){
        int i = (int) (Math.random() * 100);
        int mod = 0;
        for (int j = 0; j < modificator.length; j++){
            mod += modificator[j].getModExchangeReligion();
        }
        if (i < prob - rebelLevel + mod){
            return true;
        } else{
            return false;
        }
    }
    public void UpdateRebelLevel(int level){
        rebelLevel = 0;
        for (Modificator value : modificator) {
            rebelLevel += value.getRebelLevel();
        }
        rebelLevel -= prosperity - level;
    }
    // возвращает стоимость постройки здания
    public int CostOfBuilding(int number){
        return (int) (Math.pow(1.2, building[number].getLevel()) * World.baseCostBuild[building[number].getClassOf()] * (100 - prosperity - 2*infrastructure) / 100);
    }
    //строит здание
    public void Build(int number){
        building[number].Upgrage();
        Prosperity();
    }
    // возвращает стоимость постройки завода
    public int CostOfPlant(int number){
        if (plant.get(number) == null){
            return World.baseCostPlant * (100 - 2*prosperity) / 100;
        } else{
            return (int) ((World.baseCostPlant) * Math.pow(1.3, plant.get(number).getLevelOfPlant()) * (100 - prosperity - 2*infrastructure) / 100);
        }
    }
    public void newPlant(int resource){
        plant.add(new Plant(resource));
        Prosperity();
    }

    public int CostOfInfrastructure(){
        return (int) (Math.pow(1.4, infrastructure) * World.baseCostInfrasructure * (100 - 5* prosperity) / 100);
    }
    public void UpgradeInfrasrtucture(){
        infrastructure++;
        Prosperity();
    }

    // методы для вывода общего дохода от производства
    public void UpdateProfitFromProduction(){
        profit = 0;
        for (int i =0; i < plant.size(); i++){
            plant.get(i).UpdateProfit();
            profit += plant.get(i).getProfit();
        }
        profit *= World.baseProfitFromProduction;
    }
    // метод для вывода дохода от налогов
    public void UpdateTax(){
        tax = population * prosperity * (100 - autonomy) * World.baseProfitFromCity / 1000;
    }

    public City(int population, int infrastructure, int prosperity) {
        this.population = population;
        this.infrastructure = infrastructure;
        this.prosperity = prosperity;
    }

    public Position getPosition() {
        return position;
    }
    public int getPopulation() {
        return population;
    }

    public int getInfrastructure() {
        return infrastructure;
    }

    public int getOwner() {
        return owner;
    }

    public int getProsperity() {
        return prosperity;
    }

    public int getAutonomy() {
        return autonomy;
    }

    public ArrayList<Plant> getPlant() {
        return plant;
    }

    public int getProfit() {
        return profit;
    }

    public int getTax() {
        return tax;
    }

    public void setAutonomy(int autonomy) {
        this.autonomy = autonomy;
    }

    public int getReligion() {
        return religion;
    }

    public void setReligion(int religion) {
        this.religion = religion;
    }

    public void ActivateModificator(int i){
        modificator[i].Activate();
    }

}