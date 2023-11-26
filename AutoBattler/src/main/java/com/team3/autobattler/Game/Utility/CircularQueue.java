/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Utility;

/**
 * Reimplementing common circular queue.
 * @author Rio
 */
public class CircularQueue {
  final int size; // Size of Circular Queue
  int front, rear;
  int items[];

  CircularQueue(int size) {
    this.size = size;
    this.items = new int[size];
    front = -1;
    rear = -1;
  }

  // Check if the queue is full
  boolean isFull() {
    if (front == 0 && rear == size - 1) {
      return true;
    }
    if (front == rear + 1) {
      return true;
    }
    return false;
  }

  // Check if the queue is empty
  boolean isEmpty() {
    if (front == -1)
      return true;
    else
      return false;
  }

  // Adding an element
  void enqueue(int element) {
    if (isFull()) {
      System.out.println("Queue is full");
    } else {
      if (front == -1)
        front = 0;
      rear = (rear + 1) % size;
      items[rear] = element;
      System.out.println("Inserted " + element);
    }
  }

  // Removing an element
  int dequeue() {
    int element;
    if (isEmpty()) {
      System.out.println("Queue is empty");
      return (-1);
    } else {
      element = items[front];
      if (front == rear) {
        front = -1;
        rear = -1;
      } /* Q has only one element, so we reset the queue after deleting it. */
      else {
        front = (front + 1) % size;
      }
      return (element);
    }
  }


  @Override
  public String toString() {
    /* Function to display status of Circular Queue */
    StringBuilder stringBuilder = new StringBuilder();
    int i;
    if (isEmpty()) {
      stringBuilder.append("Empty Queue");
    } else {
      stringBuilder.append("Front -> " + front);
      for (i = front; i != rear; i = (i + 1) % size)
        stringBuilder.append(items[i] + " ");
      stringBuilder.append(items[i]);
      stringBuilder.append("Rear -> " + rear);
    }
    
    
    return stringBuilder.toString();
  }

}
