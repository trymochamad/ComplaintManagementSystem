/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetcrawler;

/**
 *
 * @author TOSHIBA PC
 */
public class Main {
    public static void main(String[] args) {
        TwitterAPI.initializeTwitter();
        TwitterAPI.TwitterSearch("@PemkotBandung OR @ridwankamil");
    }
}
