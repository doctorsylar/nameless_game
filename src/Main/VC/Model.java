package Main.VC;

import Main.Mechanics.*;

import java.util.HashMap;
import java.util.Map;

public class Model {
    static Model model = new Model();
    Map<String, String> description = new HashMap();

    private Model() {
        this.description.put("classLabel", "None");
        this.description.put("attack", "0");
        this.description.put("defence", "0");
        this.description.put("buff", "0");
        this.description.put("health", "0");
        this.description.put("od", "0");
        this.description.put("wasted", "0");
        this.description.put("level", "0");
    }

    void plusStat (String stat) {
        if (!stat.equals("health") & !stat.equals("od") & !this.description.get(stat).equals("3")) {
            this.description.put("level", String.valueOf(Integer.parseInt(this.description.get("level"))+1));
        }
        switch (this.description.get(stat)) {
            case "0" :
                this.description.put(stat, String.valueOf(Integer.parseInt(this.description.get(stat))+1));
                this.description.put("wasted", String.valueOf(Integer.parseInt(this.description.get("wasted"))+1));
                break;
            case "1" :
                this.description.put(stat, String.valueOf(Integer.parseInt(this.description.get(stat))+1));
                this.description.put("wasted", String.valueOf(Integer.parseInt(this.description.get("wasted"))+2));
                break;
            case "2" :
                this.description.put(stat, String.valueOf(Integer.parseInt(this.description.get(stat))+1));
                this.description.put("wasted", String.valueOf(Integer.parseInt(this.description.get("wasted"))+3));
                break;
            case "3" :
                break;
        }
    }
    void minusStat (String stat) {
        if (!stat.equals("health") & !stat.equals("od") & !this.description.get(stat).equals("0")) {
            this.description.put("level", String.valueOf(Integer.parseInt(this.description.get("level"))-1));
        }
        switch (this.description.get(stat)) {
            case "0" :
                break;
            case "1" :
                this.description.put(stat, String.valueOf(Integer.parseInt(this.description.get(stat))-1));
                this.description.put("wasted", String.valueOf(Integer.parseInt(this.description.get("wasted"))-1));
                break;
            case "2" :
                this.description.put(stat, String.valueOf(Integer.parseInt(this.description.get(stat))-1));
                this.description.put("wasted", String.valueOf(Integer.parseInt(this.description.get("wasted"))-2));
                break;
            case "3" :
                this.description.put(stat, String.valueOf(Integer.parseInt(this.description.get(stat))-1));
                this.description.put("wasted", String.valueOf(Integer.parseInt(this.description.get("wasted"))-3));
                break;
        }
    }

    public static Model getModelInstance() {
        return model;
    }
}
