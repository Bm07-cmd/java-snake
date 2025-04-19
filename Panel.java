/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author tcsmi
 */
class Panel extends JPanel implements ActionListener, KeyListener{
    
    int TILE_SIZE = Settings.TILE_SIZE;
    
    Dimension resolution;
    Rect rect;
    Timer loop;
    
    
    ArrayList<SnakeBodyRect> snake;

    
    enum Direction{ UP, DOWN, LEFT, RIGHT, NONE }
    Direction current_direction = Direction.NONE;
    
    Random random;
    int random_x;
    int random_y;
    Rect food;
    
    Panel(int w, int h){
        this.resolution = new Dimension(w, h);
        this.setPreferredSize(resolution);
        this.setBackground(Color.black);
        
        this.loop = new Timer(60, this);
        this.loop.start();
        
        this.rect = new Rect(100, 100, this.TILE_SIZE - 1, this.TILE_SIZE  - 1, Color.DARK_GRAY);
        
        this.snake = new ArrayList<SnakeBodyRect>();
        for(int i = 0; i < 3; i++){
            SnakeBodyRect body_rect = new SnakeBodyRect(this.TILE_SIZE * (i + 10), this.TILE_SIZE * 10, this.TILE_SIZE, this.TILE_SIZE, Color.GREEN);
            this.snake.add(body_rect);
        }
        
        this.addKeyListener(this);
        this.setFocusable(true);
        
        this.random = new Random();
        this.shuffleFoodPosition();
        this.food = new Rect(this.random_x, this.random_y, this.TILE_SIZE, this.TILE_SIZE, Color.RED);
        
        
    }
    
    public void shuffleFoodPosition(){
        this.random_x = this.random.nextInt(Settings.ROWS) * this.TILE_SIZE;
        this.random_y = this.random.nextInt(Settings.COLUMNS) * this.TILE_SIZE;
        
    }
        
    public void renderGrid(Graphics g){
        for(int i = 0; i < Settings.ROWS; ++i){
            for(int j = 0; j < Settings.COLUMNS; ++j){
                this.rect.x_pos = i * Settings.TILE_SIZE;
                this.rect.y_pos = j * Settings.TILE_SIZE;
                this.rect.draw(g);
            }
        }
    }
    
    public void renderSnake(Graphics g){
        this.snake.forEach((body) -> {
            body.draw(g);
        });
    }
    
    public void updateSnake(){
        int snakeSize = this.snake.size() - 1;
        
        int last_x = this.snake.get(snakeSize).x_pos;
        int last_y = this.snake.get(snakeSize).y_pos;
        
        
        //int last_y = this.snake.getLast().y_pos;
        SnakeBodyRect head = new SnakeBodyRect(last_x, last_y, this.TILE_SIZE, this.TILE_SIZE, Color.GREEN);
        int current_x = head.getX();
        int current_y = head.getY();
        
        switch(this.current_direction){
            
            case RIGHT:
                head.setX(current_x += this.TILE_SIZE);
                this.snake.add(head);
                this.snake.remove(0);
            break;
            
            case LEFT:
                head.setX(current_x -= this.TILE_SIZE);
                this.snake.add(head);
                this.snake.remove(0);
            break;
            
            case UP:
                head.setY(current_y -= this.TILE_SIZE);
                this.snake.add(head);
                this.snake.remove(0);
            break;
                
            case DOWN:
                head.setY(current_y += this.TILE_SIZE);
                this.snake.add(head);
                this.snake.remove(0);
            break;
            
            
            
        }
    }
   
    
    public void checkFoodCollision(){
        int snakeSize = this.snake.size() - 1;
        if(this.snake.get(snakeSize).x_pos == this.food.x_pos && this.snake.get(snakeSize).y_pos == this.food.y_pos ){
            this.shuffleFoodPosition();
            this.food.x_pos = this.random_x;
            this.food.y_pos = this.random_y;
        
        int last_x = this.snake.get(snakeSize).x_pos;
        int last_y = this.snake.get(snakeSize).y_pos;
        
        
        //int last_y = this.snake.getLast().y_pos;
        SnakeBodyRect head = new SnakeBodyRect(last_x, last_y, this.TILE_SIZE, this.TILE_SIZE, Color.GREEN);
        this.snake.add(head);
        
        }
    }

    public void checkGameOver(){
        int snakeSize = this.snake.size() - 1;
            for(int i = 0; i < this.snake.size() - 1; ++i) {
		if(this.snake.get(snakeSize).getX() == this.snake.get(i).getX() && this.snake.get(snakeSize).getY() == this.snake.get(i).getY()) {
			this.loop.stop();
		    }
		}
		if(this.snake.get(snakeSize).getX() >= Settings.WINDOW_WIDTH || this.snake.get(snakeSize).getY() == Settings.WINDOW_HEIGHT) {
			this.loop.stop();
		}
		else if(this.snake.get(snakeSize).getX() < 0 || this.snake.get(snakeSize).getY() < 0) {
			this.loop.stop();
		}
            
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        this.renderGrid(g);
        this.food.draw(g);
        this.renderSnake(g);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        this.updateSnake();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            //this.current_direction = Direction.RIGHT;
            this.current_direction = Direction.RIGHT;
        }
        
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(this.current_direction != Direction.UP){
                 this.current_direction = Direction.DOWN;
            }
        }
        
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(this.current_direction != Direction.RIGHT){
                this.current_direction = Direction.LEFT;
            }
        }
        
        else if(e.getKeyCode() == KeyEvent.VK_UP){
            if(this.current_direction != Direction.DOWN){
                this.current_direction = Direction.UP;
            }
        }
        
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
