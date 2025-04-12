/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Color;

/**
 *
 * @author tcsmi
 */
class SnakeBodyRect extends Rect{
    
    SnakeBodyRect(int x, int y, int w, int h, Color c) {super(x, y, w, h, c);}
    
    public void setX(int x){this.x_pos = x;}
    public void setY(int y) {this.y_pos = y;}
    public int getX(){return this.x_pos;}
    public int getY() {return this.y_pos;}
}
