package com.mygdx.game.Entities;

public class Region {
    private int squareOfGround = 10;
    private Position position;
    private City[] city;
    private int population;
    private int prosperity;
    private int resource;
    private int infrastructure = 1;
    private int autonomy;
    private int mineral;
    private int baseMineralProduction = 1;
    private int effectivity = 1;
    private int profitRR;
    private int profitMineral;
    private int religion;
    private int rebelLevel;
    private int culture;

    private int numberOfModificators = 0;
    private Modificator[] modificator = new Modificator[numberOfModificators];

    public void Prosperity(){
        int i = (int) (Math.random() * 100);
        if (i < 6 - prosperity){
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
    public boolean ExchangeCulture(int prob){
        int i = (int) (Math.random() * 1000);
        int mod = 0;
        for (int j = 0; j < modificator.length; j++){
            mod += modificator[j].getModExchangeCulture();
        }
        if (i < prob - rebelLevel * 2 + mod){
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

    public Region(City[] city, int population, int prosperity, int squareOfGround) {
        this.city = city;
        this.population = population;
        this.prosperity = prosperity;
    }

    // эти два метода сделаны для вывода в окно региона
    public void UpdateProfitRR() {
        profitRR = Math.min(population, squareOfGround * effectivity) * World.valueRR[resource] * infrastructure * (100 - autonomy) * World.baseProfitFromRegion / 100;
    }

    public void UpdateProfitMineral() {
        profitMineral = (5 + infrastructure) * World.valueMineral[mineral] * (100 - autonomy) * baseMineralProduction * World.baseProfitFromMineral / 100;
    }


    public int getSquareOfGround() {
        return (squareOfGround);
    }

    public int getPopulation() {
        return (population);
    }

    public int getProsperity() {
        return (prosperity);
    }

    public int getResource() {
        return (resource);
    }

    public int getInfrastructure() {
        return infrastructure;
    }

    public int getAutonomy() {
        return autonomy;
    }

    public int getMineral() {
        return mineral;
    }

    public int getBaseMineralProduction() {
        return baseMineralProduction;
    }

    public City[] getCity() {
        return city;
    }

    public int getEffectivity() {
        return effectivity;
    }

    public int getProfitRR() {
        return profitRR;
    }

    public int getProfitMineral() {
        return profitMineral;
    }

    public void setAutonomy(int autonomy) {
        this.autonomy = autonomy;
    }

    public Position getPosition() {
        return position;
    }

    public int getReligion() {
        return religion;
    }

    public void setReligion(int religion) {
        this.religion = religion;
    }

    public int getRebelLevel() {
        return rebelLevel;
    }

    public int getCulture() {
        return culture;
    }

    public void setCulture(int culture) {
        this.culture = culture;
    }

    public void ActivateModificator(int i){
        modificator[i].Activate();
    }

}

