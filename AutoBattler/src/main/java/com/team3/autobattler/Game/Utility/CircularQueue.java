/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Utility;

/**
 * The CircularQueue class represents a circular queue data structure with a fixed size.
 * It provides methods for checking if the queue is full or empty, adding elements to the
 * queue (enqueue), removing elements from the queue (dequeue), and displaying the current
 * status of the queue.
 *
 * @author [Your Name]
 */
public class CircularQueue {
  final int size; // Size of Circular Queue
  int front, rear;
  int items[];

    /**
     * Constructs a CircularQueue with the specified size.
     *
     * @param size The size of the Circular Queue.
     */
  public CircularQueue(int size) {
    this.size = size;
    this.items = new int[size];
    front = -1;
    rear = -1;
  }

  /**
     * Checks if the circular queue is full.
     *
     * @return True if the queue is full, false otherwise.
     */
  boolean isFull() {
    if (front == 0 && rear == size - 1) {
      return true;
    }
    if (front == rear + 1) {
      return true;
    }
    return false;
  }

  /**
     * Checks if the circular queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
  boolean isEmpty() {
    if (front == -1)
      return true;
    else
      return false;
  }

   /**
     * Adds an element to the circular queue.
     *
     * @param element The element to be added.
     */
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

  /**
     * Removes and returns an element from the circular queue.
     *
     * @return The removed element, or -1 if the queue is empty.
     */
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

  /**
     * Provides a string representation of the CircularQueue's current status.
     *
     * @return A string displaying the contents and status of the queue.
     */
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
