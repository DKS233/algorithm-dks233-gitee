package week307;

/**
 * 6152. 赢得比赛需要的最少训练时长
 *
 * @author dks233
 * @create 2022-08-21-10:11
 */
public class One {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int result = 0;
        for (int i = 0; i < energy.length; i++) {
            if (initialEnergy > energy[i]) {
                initialEnergy -= energy[i];
            } else {
                int add = energy[i] - initialEnergy + 1;
                initialEnergy += add;
                initialEnergy -= energy[i];
                result += add;
            }
            if (initialExperience > experience[i]) {
                initialExperience += experience[i];
            } else {
                int add = experience[i] - initialExperience + 1;
                initialExperience += add;
                initialExperience += experience[i];
                result += add;
            }
        }
        return result;
    }

}
