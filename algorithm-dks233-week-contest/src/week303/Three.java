package week303;

import java.util.*;

/**
 * 6126. 设计食物评分系统
 *
 * @author dks233
 * @create 2022-07-24-10:18
 */
public class Three {
    public static class FoodRatings {
        HashMap<String, Node> foodNodeMap; // 食物->node
        HashMap<String, PriorityQueue<Node>> cuisinessNodeMap; // 烹饪方式->node优先级队列

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            foodNodeMap = new HashMap<>();
            cuisinessNodeMap = new HashMap<>();
            for (int index = 0; index < foods.length; index++) {
                // 注：foodNodeMap和cuisinessNodeMap的优先级队列中添加的node应该是同一个
                Node node = new Node(foods[index], cuisines[index], ratings[index]);
                foodNodeMap.putIfAbsent(foods[index], node);
                if (!cuisinessNodeMap.containsKey(cuisines[index])) {
                    cuisinessNodeMap.put(cuisines[index], new PriorityQueue<>((nodeOne, nodeTwo) -> nodeOne.rating
                            == nodeTwo.rating ? nodeOne.food.compareTo(nodeTwo.food) : nodeTwo.rating - nodeOne.rating));
                }
                cuisinessNodeMap.get(cuisines[index]).add(node);
            }
        }

        public void changeRating(String food, int newRating) {
            // 更新foodNodeMap
            // 更新cuisinessNodeMap
            Node node = foodNodeMap.get(food);
            PriorityQueue<Node> queue = cuisinessNodeMap.get(node.cuisine);
            queue.remove(node);
            node.rating = newRating;
            queue.offer(node);
        }

        public String highestRated(String cuisine) {
            PriorityQueue<Node> queue = cuisinessNodeMap.get(cuisine);
            return queue.peek() == null ? "" : queue.peek().food;
        }

        public static class Node {
            String food;
            String cuisine;
            int rating;

            public Node(String food, String cuisine, int rating) {
                this.food = food;
                this.cuisine = cuisine;
                this.rating = rating;
            }
        }
    }
}
