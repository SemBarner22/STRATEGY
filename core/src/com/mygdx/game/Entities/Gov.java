package com.mygdx.game.Entities;

import com.mygdx.game.Entities.Adv.*;
import com.mygdx.game.Entities.Estate.Estate;

import java.util.ArrayList;

import static com.sun.tools.doclint.Entity.and;

public class Gov {
    // мдификаторы
    private int modAdvisorCost = 0;
    private int modBuildingCost = 0;
    private int modArmyCreation = 0;
    private int modRebel = 0;
    private int modInterest = 0;
    private int modPrestige = 0;
    private int modLegecimacy = 0;
    private int modAdm = 0;
    private int modAutonomy = 0;
    private boolean isPlayer = true;
    private int profitFromEstates = 10;
    private int[] powerIncrease = new int[World.baseNumberOfEstates];
    private int[] loyalityIncrease = new int[World.baseNumberOfEstates];
    private int modShock; //в процентах
    private int modFire;
    private int modTactic;
    private int modExchangeReligion;
    private int modExchangeCulture;
    private int modProfitFromProduction;
    private int modProfitFromRegion;
    private int modProfitFromMineral;
    private int modProfitFromCity;

    private Modificator[] modificator;
    // обновляем все моды; сначала обнуляем затем добавляем во всех структурах, которые влияют на них
    public void UpdateMod(){
        modBuildingCost = 0;
        modTactic = 0;
        modShock = 0;
        modFire = 0;
        modProfitFromProduction = 0;
        modProfitFromRegion = 0;
        modProfitFromMineral = 0;
        modProfitFromCity = 0;
        for (int i = 0; i < advList.size(); i++){
            if (advList.get(i).getHaveJob() > 0) {
                modBuildingCost += advList.get(i).getModBuildingCost();
                modTactic += advList.get(i).getModTactic();
                modShock += advList.get(i).getModShock();
                modFire += advList.get(i).getModFire();
            }
        }
        for (int i = 0; i < modificator.length; i++){
            if (modificator[i].getIs() == 1) {
                modBuildingCost += modificator[i].getModBuildingCost();
                modTactic += modificator[i].getModTactic();
                modFire += modificator[i].getModFire();
                modShock += modificator[i].getModShock();
            }
        }
        for (int i = 0; i < estate.length; i++){
            if (estate[i].getIsInLobby() == 1) {
                modBuildingCost += estate[i].getModBuildingCost() * estate[i].getIsInLobby();
                modTactic += estate[i].getTactic();
                modProfitFromProduction += estate[i].getProfitFromProduction();
                modProfitFromRegion += estate[i].getProfitFromRegion();
                modProfitFromMineral += estate[i].getProfitFromMineral();
                modProfitFromCity += estate[i].getProfitFromCity();
                PlusMoney(estate[i].getPlusMoney() * profit / 10);
                estate[i].setPlusMoney(0);
            }
        }

    }

    //доходы
    private int money;
    private int profitFromProduction;
    private int profitFromRegion;
    private int profitFromMineral;
    private int profitFromCity;
    private int profit;

    // расходы
    private int cost;
    private int costArmy;
    private int costAdm;

    private int modCostAdm = 1;
    private int modCostArmy = 1;
    // займы
    private int interest = World.baseInterest;
    private ArrayList<Debt> debt = new ArrayList<Debt>();
    private int maxDebt;
    private int costDebt;

    // другие ресурсы
    private int adm;
    private int prestige; //от -10000 до 10000. Показываются только первые 3 цифры
    private int legicimacy; //по-разному. Пока что делаю для королевства также, как престиж

    // государственное устройство
    private ArrayList<Region> region;
    private Position capital;
    private int religion;
    private int culture;

    // армия
    public ArrayList<Army> army = new ArrayList<Army>();


    // советники
    private ArrayList<Advisor> advList = new ArrayList<Advisor>();
    private ArrayList<General> general = new ArrayList<General>();
    // призываем советника
    public void CreateAdvisor(String adv){
        if (adm > World.baseAdvisorCost * ( 100 + modAdvisorCost) /100){
            adm -= World.baseAdvisorCost * ( 100 + modAdvisorCost) /100;
            if (adv.equals("Diplomat")){
                advList.add(new Diplomat());
            }
            if (adv.equals("Cleric")){
                advList.add(new Cleric());
            }
            if (adv.equals("Financier")){
                advList.add(new Financier());
            }
            if (adv.equals("General")){
                General gen = new General();
                general.add(gen);
                advList.add(gen);
            }
            if (adv.equals("Judge")){
                advList.add(new Judge());
            }
            if (adv.equals("Scientist")){
                advList.add(new Scientist());
            }
            if (adv.equals("Spy")){
                advList.add(new Spy());
            }
        }
    }
    // назначаем советника  в ячейку number
    public void AssignAdisor(int adv, int number){
        advList.get(adv).setHaveJob(number);
    }
    // убираем советника
    public void DismisAdvisor(int adv){
        advList.get(adv).setHaveJob(0);
    }
    // убиваем советника
    public void KiilAdvisor(int adv){
        advList.remove(adv);
    }
    // выдаем список незанятых советников
    public Integer[] GetUnasignAdvisors(){
        ArrayList<Integer> ar = new ArrayList<Integer>();
        for (int i = 0; i<advList.size(); i++){
            if (advList.get(i).getHaveJob() == 0){
                ar.add(i);
            }
        }
        return ar.toArray(new Integer[0]);
    }
    // выдаем советника стоящего на месте number
    public int AdvisorNumber(int number){
        for (int i = 0; i <advList.size(); i++){
            if (advList.get(i).getHaveJob() == number){
                return i;
            }
        }
        return -1;
    }
    // Увеличение возраста всех советников
    public void UpAge(){
        int i = 0;
        while (i < advList.size()){
            advList.get(i).AgeUp();
            if (advList.get(i).Death()){
                KiilAdvisor(i);
            } else {
                i++;
            }
        }
    }


    // Сословия
    private Estate[] estate;
    public void UpdateEstate(){
        int commonPower = 0;
        for (int i =0; i < estate.length; i++){
            loyalityIncrease[i] = World.baseLoyalityIncrease;
            estate[i].setLoyalityIncrease(loyalityIncrease[i]);
            powerIncrease[i] = estate[i].getIsInLobby();
            estate[i].setPowerIncrease(powerIncrease[i]);
            estate[i].UpdateLP();
            commonPower += estate[i].getPower();
        }
        for (int i = 0; i<estate.length; i++){
            estate[i].setPartOfPover(estate[i].getPower() / (commonPower / 10));
            estate[i].UpdateBonus();
        }

    }
    public void ExchangeEstate(int newOne, int oldOne){
        estate[newOne].setPower(estate[oldOne].getPower() / 2 + estate[newOne].getPower());
        estate[oldOne].setPower(estate[oldOne].getPower() / 2);
    }



    public Gov(ArrayList<Region> region) {
        this.region = region;

    }
    private void NullArray(int[] array){
        for (int j = 0; j < array.length; j++){
            array[j] = 0;
        }
    }
    //обновляем производство и спрос на все ресурсы. Заодно обновляем религию, культуру и восстания
    public void UpdatePD() {
        for (int i = 0; i < region.size(); i++) {
            World.totalRegionProduction[region.get(i).getResource()] += Math.min(region.get(i).getPopulation(), region.get(i).getSquareOfGround()* region.get(i).getEffectivity()) * World.baseProfitFromRegion;
            World.totalMineralProduction[region.get(i).getMineral()] += (5 + region.get(i).getInfrastructure()) * region.get(i).getBaseMineralProduction() * (50 + region.get(i).getProsperity()) * World.baseProfitFromMineral;
            int mod = 0; // модификатор восстания из-за культуры и религии
            if (culture != region.get(i).getCulture()){
                mod +=2;
            }
            if (region.get(i).getReligion() != religion){
                mod +=4;
            }
            region.get(i).UpdateRebelLevel(modRebel + mod);
            if (region.get(i).ExchangeReligion(World.baseChanceOfChangingReligion + modExchangeReligion) && region.get(i).getReligion() != religion){
                region.get(i).setReligion(religion);
            }
            if (region.get(i).ExchangeCulture(World.baseChanceOfChangingCulture + modExchangeCulture) && region.get(i).getCulture() != culture){
                region.get(i).setCulture(culture);
            }
            for (int j = 0; j < region.get(i).getCity().length; j++) {
                mod = 0;
                if (culture != region.get(i).getCulture()){
                    mod +=2;
                }
                if (region.get(i).getCity()[j].getReligion() != religion){
                    mod +=4;
                }
                region.get(i).getCity()[j].UpdateRebelLevel(modRebel + mod);
                if (region.get(i).getCity()[j].ExchangeReligion(World.baseChanceOfChangingReligion + modExchangeReligion) && region.get(i).getCity()[j].getReligion() != religion){
                    region.get(i).setReligion(religion);
                }
                for (int k = 0; k < region.get(i).getCity()[j].getPlant().size(); k++) {
                    World.totalCityProduction[region.get(i).getCity()[j].getPlant().get(k).getResourceOfPlant()] += region.get(i).getCity()[j].getPlant().get(k).getLevelOfPlant() * World.baseProfitFromProduction;
                    for (int l = 0; l < World.numberOfRR; l++){
                        World.totalPlantRRDemand[l] += region.get(i).getCity()[j].getPlant().get(k).getDemand("RR", l);
                    }
                    for (int l = 0; l < World.numberOfMineral; l++){
                        World.totalPlantMineralDemand[l] +=region.get(i).getCity()[j].getPlant().get(k).getDemand("Mineral", l);
                    }
                    for (int l = 0; l < World.numberOfCR; l++){
                        World.totalPlantCRDemand[l] += region.get(i).getCity()[j].getPlant().get(k).getDemand("CR", l);

                    }
                }
            }
        }
    }

    // обновляем базовый доход всего государства и максимальный заем. Обновляем автономию
    private void UpdateProfit () {
        profitFromRegion = 0;
        profitFromCity = 0;
        profitFromProduction = 0;
        profitFromCity = 0;
        for (int i = 0; i < region.size(); i++){
            // обновляем доход от регионов
            int mod = 0; // модификатор автономии из-за культуры и религии
            if (culture != region.get(i).getCulture()){
                mod +=10;
            }
            if (region.get(i).getReligion() != religion){
                mod +=20;
            }
            int aut;
            aut = (int) (Math.tan((Math.pow(capital.GetX()-region.get(i).getPosition().GetX(), 2) + Math.pow(region.get(i).getPosition().GetY() - capital.GetY(), 2))) / Math.sqrt(Math.pow(World.heigthOfMap, 2) + Math.pow(World.wideOfMap, 2)) * World.baseAutonomy)+mod;
            if (aut > 100){
                aut =100;
            }
            region.get(i).setAutonomy(aut);
            region.get(i).UpdateProfitRR();
            profitFromRegion += region.get(i).getProfitRR();
            region.get(i).UpdateProfitMineral();
            profitFromMineral += region.get(i).getProfitMineral();
            for (int j = 0; j < region.get(i).getCity().length; j++){
                mod = 0;
                if (culture != region.get(i).getCulture()){
                    mod +=10;
                }
                if (region.get(i).getCity()[j].getReligion() != religion){
                    mod +=20;
                }
                aut = (int) (Math.tan((Math.pow(capital.GetX()-region.get(i).getCity()[j].getPosition().GetX(), 2) + Math.pow(region.get(i).getCity()[j].getPosition().GetY() - capital.GetY(), 2))) / (Math.pow(World.heigthOfMap, 2) + Math.pow(World.wideOfMap, 2)) * World.baseAutonomy) + mod;
                if (aut > 100){
                    aut =100;
                }
                region.get(i).getCity()[j].setAutonomy(aut);
                //обновляем доход от городов
                region.get(i).getCity()[j].UpdateProfitFromProduction();
                profitFromProduction +=  region.get(i).getCity()[j].getProfit();
                region.get(i).getCity()[j].UpdateTax();
                profitFromCity += region.get(i).getCity()[j].getTax();
            }
        }

        profitFromRegion *= 100 + modProfitFromRegion;
        profitFromCity *= 100 + modProfitFromCity;
        profitFromMineral *= 100 + modProfitFromMineral;
        profitFromProduction *= 100 + modProfitFromProduction;
        profitFromRegion /= 100;
        profitFromCity /= 100;
        profitFromMineral /= 100;
        profitFromProduction /= 100;

        profit = profitFromProduction + profitFromRegion + profitFromMineral + profitFromCity;
        maxDebt = 4 * profit;
    }
    //расходы - армия, бюрократия,

    //обновляем расходы на армию
    private void UpdateCostArmy () {
        costArmy = 0;
        for (int i = 0; i <army.size(); i++){
            costArmy += army.get(i).GetCost();
        }
        costArmy *= World.baseCostArmy * modCostArmy;
    }
    // обновляем расход на бюрократию
    private void UpdateCostAdm () {
        int n = region.size();
        for (int i = 0; i <region.size(); i++){
            n += region.get(i).getCity().length;
        }
        costAdm = n * World.baseCostAdm * modCostAdm;
    }
    // обновляем расход на долги. Можно использовать только раз в ход
    private void UpdateCostDebt () {
        costDebt = 0;
        for (int i = 0; i <debt.size(); i++){
            while (debt.get(i).getTime() == 0) {
                money -= debt.get(i).getSum();
                debt.remove(i);
            }
            if (i < debt.size()) {
                costDebt += debt.get(i).getSum() * debt.get(i).getInterest();
                debt.get(i).PayDay();
            }
        }
    }
    //обновляем все расходы
    private void UpdateCost() {
        UpdateCostArmy();
        UpdateCostAdm();
        UpdateCostDebt();

    }
    // этот класс сделан специально, чтобы пересчитывать при найме армии и других изменниях костов
    public void ReCountCost(){
        cost = costArmy + costAdm + costDebt;
    }
    // обновляем профит от сословий
    public void UpdateProfitFromEstates(){
        UpdateEstate();
        profitFromEstates = 1;
        for (int i = 0; i < estate.length; i++){
            if (estate[i].getIsInLobby() == 1) {
                profitFromEstates *= estate[i].getProfit();
            }
        }
    }
    //изменение казны
    public void MakeMoney() {
        UpAge();
        UpdateMod();

        UpdateAPL();
        UpdateProfitFromEstates();
        UpdateProfit();
        UpdateCost();
        ReCountCost();
        money += profit * profitFromEstates / 100000 - cost;
        while (!CheckMoney(0)){
            TakeDebt();
        }
        //надо сделать проверку на количество долгов и сделать банкротство вообще надо придуать, что надоделать
    }
    // проверка на наличие суммы денег
    public boolean CheckMoney( int number){
        if (number < money) {
            return false;
        } else{
            return true;
        }
    }
    // получаем деньги
    public void PlusMoney(int m){
        money += m;
    }
    //берем в долг
    public void TakeDebt(){
        debt.add(new Debt(maxDebt, interest, 10));
        money += maxDebt;
    }
    //обновляем другие ресурсы
    public void UpdateAPL() {
        adm += World.baseAdm + modAdm;
        prestige -= prestige * (100 - World.basePrestige) / 100 - modPrestige;
        legicimacy += World.baseLegicimacy + modLegecimacy;
    }

    // строительство
    // проверяем воможно ли построть
    public boolean posBuild(int numberOfRegion, int numberOfCity, int numberOfBuilding){
        if (CheckMoney(region.get(numberOfRegion).getCity()[numberOfCity].CostOfBuilding(numberOfBuilding) * modBuildingCost / 100)){
            return true;
        } else{
            return false;
        }
    }
    // строим здание
    public void Build(int numberOfRegion, int numberOfCity, int numberOfBuilding){
        if (posBuild(numberOfRegion, numberOfCity, numberOfBuilding)){
            region.get(numberOfRegion).getCity()[numberOfCity].Build(numberOfBuilding);
            PlusMoney(- region.get(numberOfRegion).getCity()[numberOfCity].CostOfBuilding(numberOfBuilding) * modBuildingCost / 100);
        }
    }
    // проверяем можем ли построить/улучшить завод
    public  boolean posBuildPlant(int numberOfRegion, int numberOfCity, int numberPlant /*номер клетки*/){
        if (CheckMoney(region.get(numberOfRegion).getCity()[numberOfCity].CostOfPlant(numberPlant) * modBuildingCost / 100)){
            return true;
        } else {
            return false;
        }
    }
    // строим завод
    public void BuildPlant(int numberOfRegion, int numberOfCity, int numberPlant, int resource){
        if (posBuildPlant(numberOfRegion, numberOfCity, numberPlant)){
            if (numberPlant > region.get(numberOfRegion).getCity()[numberOfCity].getPlant().size()){
                region.get(numberOfRegion).getCity()[numberOfCity].newPlant(resource);
            } else {
                region.get(numberOfRegion).getCity()[numberOfCity].getPlant().get(numberPlant).Upgrade();
            }
            PlusMoney(- region.get(numberOfRegion).getCity()[numberOfCity].CostOfPlant(numberPlant) * modBuildingCost / 100);
        }
    }

    public int getModTactic() {
        return modTactic;
    }

    public void ActivateModificator(int i){
        modificator[i].Activate();
    }

    public int getModShock() {
        return modShock;
    }

    public int getModFire() {
        return modFire;
    }
}