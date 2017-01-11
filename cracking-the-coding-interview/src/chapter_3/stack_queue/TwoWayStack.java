package chapter_3.stack_queue;

import datastructures.stack.AbstractStack;

public class TwoWayStack<T> extends AbstractStack<T> {
  NodeTopBottom<T> top = null;
  NodeTopBottom<T> bottom = null;

  @Override
  public void push(T data) {
    if (top == null) {
      top = new NodeTopBottom<T>(data);
      bottom = top;
    } else {
      NodeTopBottom<T> node = new NodeTopBottom<T>(data);
      top.above = node;
      node.below = top;
      top = node;
    }
    size++;
  }

  @Override
  public T pop() {
    T data = null;
    if (!isEmpty()) {
      if (top.equals(bottom)) {
        data = top.data;
        top = null;
        bottom = null;
      } else {
        data = top.data;
        top.below.above = null;
        top = top.below;
      }
      size--;
    }

    return data;
  }

  public T popBottom() {
    T data = null;
    if (!isEmpty()) {
      if (bottom.equals(top)) {
        data = bottom.data;
        bottom = null;
        top = null;
        size--;
      }

      if (bottom != null) {
        data = bottom.data;
        bottom.above.below = null;
        bottom = bottom.above;
        size--;
      }
    }
    return data;
  }

  @Override
  public T peek() {
    T data = null;
    if (top != null) {
      data = top.data;
    }
    return data;
  }

  public T peekBelow() {
    T data = null;
    if (bottom != null) {
      data = bottom.data;
    }
    return data;
  }

  /**
   * @Override toString() Returns the content of the stack
   */
  @Override
  public String toString() {
    if (this.isEmpty()) {
      return "";
    }

    NodeTopBottom<T> current = top;
    StringBuilder builder = new StringBuilder();
    builder.append("[");
    while (current != null) {
      builder.append(current.data);
      builder.append((current.below != null) ? " " : "]");
      current = current.below;
    }
    return builder.toString();
  }

  @SuppressWarnings("hiding")
  class NodeTopBottom<T> {
    T data;
    NodeTopBottom<T> above;
    NodeTopBottom<T> below;

    public NodeTopBottom(T data) {
      this.data = data;
    }
  }

}
