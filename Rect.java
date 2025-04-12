/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author tcsmi
 */
class Rect {
    int x_pos, y_pos, width, height;
    Color color;
    
    Rect(int x, int y, int w, int h, Color c){
        this.x_pos = x;
        this.y_pos = y;
        this.width = w;
        this.height = h;
        this.color = c;
    }
    
    public void draw(Graphics g){
        g.setColor(this.color);
        g.fillRect(this.x_pos, this.y_pos, this.width, this.height);
    }
}
