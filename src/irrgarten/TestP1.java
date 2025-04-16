/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import irrgarten.UI.TextUI;
import irrgarten.controller.Controller;
/**
 * Prueba practica 1
 * @author deibyss
 */
public class TestP1 {
    public static void main(String[] args){
        Game partida = new Game(2);
        Controller controlador = new Controller(partida,new TextUI());
        controlador.play();
    }
}
