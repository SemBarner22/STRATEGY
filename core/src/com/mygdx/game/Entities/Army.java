package com.mygdx.game.Entities;
import com.mygdx.game.Entities.Adv.General;
import com.mygdx.game.Entities.World;

public class Army {
    private int lineInf;
    private int grenad;
    private int eger;
    private int ulan;
    private int dragun;
    private int kirassir;
    private int heavyArt;
    private int lightArt;
    private int amount;
    private int morale;
    private int maxMorale = 15000;
    private int tactic;
    private int organization;
    private int equipment; // это средняя экипированность на тысячу солдат. В начале игры будет где то 1000, к концу дойдет до 2000. Увеличивает как урон от огня, так и защиту
    private int movement;
    private int maxMovement = World.baseMaxMovement;
    private int prof; //0 если ополчение, 1 если наемная армия
    private General general;
    private Position position;
    private int fire;
    private int shock;

    private int modFire = 1;
    private int modShock = 1;

    public void Lose(int i){
        lineInf =  (lineInf * i / 100);
        grenad =  (grenad * i / 100);
        eger =  (eger * i / 100);
        ulan =  (ulan * i / 100);
        dragun =  (dragun * i / 100);
        kirassir =  (kirassir * i / 100);
        heavyArt =  (heavyArt * i / 100);
        lightArt =  (lightArt * i / 100);
        morale = morale * (100 - 2 * (100 - i)) * 100 / maxMorale ;
        organization = organization * i / 100;
    }

    public Army(int lineInf, int grenad, int eger, int ulan, int dragun, int kirassir, int heavyArt, int lightArt, int morale, int organization, int equipment, Position position, int prof) {
        this.lineInf = lineInf;
        this.grenad = grenad;
        this.eger = eger;
        this.ulan = ulan;
        this.dragun = dragun;
        this.kirassir = kirassir;
        this.heavyArt = heavyArt;
        this.lightArt = lightArt;
        this.morale = morale;
        this.organization = organization;
        this.equipment = equipment;
        this.position = position;
    }
    public void ArmyMove(Position nextPosition){

    }
    public void UpdateTactic(int govTac){
        tactic = (govTac + general.getTactic()) * 4;
    }
    public void UpdateSF(){
        amount = lightArt + lineInf + grenad + eger + ulan +dragun + kirassir + heavyArt;
        shock = (lineInf * 5 + grenad * 6 + eger * 5 + (ulan * 15 + dragun * 12 + kirassir * 18) * (10 + 3 * general.getBonus()[2]) / 10) / 100 * ((morale + 500) / 100)  * modShock * (100 + general.getShock())* (10 + general.getBonus()[0]) * (25 + tactic) / 2500000;
        fire = ((lineInf * 50 + grenad * 60 + eger * 70)* (10 + 2 * general.getBonus()[3]) / 10  + dragun * 40 + kirassir * 30 + heavyArt * 220 + lightArt * 100) / 1000 * modFire * organization / 100 * (100 + general.getFire()) * (10 + general.getBonus()[1])* (25 + tactic)/25000;
    }


    public int getMorale() {
        return morale;
    }

    public int getTactic() {
        return tactic;
    }

    public int getOrganization() {
        return organization;
    }

    public int getEquipment() {
        return equipment;
    }

    public int GetCost(){
        return ((lineInf + grenad + eger) * 10 + (ulan + dragun + kirassir) * 15 + (lightArt + heavyArt) * 20) * (prof+1);
    }
    public int getFire() {
        return fire;
    }

    public int getShock() {
        return shock;
    }

    public int getMovement() {
        return movement;
    }

    public General getGeneral() {
        return general;
    }

    public Position getPosition() {
        return position;
    }

    public void setModFire(int modFire) {
        this.modFire = modFire;
    }

    public void setModShock(int modShock) {
        this.modShock = modShock;
    }

    public int getAmount() {
        return amount;
    }
}
