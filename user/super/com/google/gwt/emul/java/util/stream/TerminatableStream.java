package java.util.stream;

//package protected, as not part of jre
class TerminatableStream {
  private boolean terminated = false;
  private final TerminatableStream previous;

  public TerminatableStream(TerminatableStream previous) {
    this.previous = previous;
  }

  void throwIfTerminated() {
    if (terminated) {
      throw new IllegalStateException("Stream already terminated, can't be modified or used");
    }
  }
  //note that not all terminals directly call this, but they must use it indirectly
  void terminate() {
    //no terminals work if already terminated
    throwIfTerminated();
    terminated = true;
    if (previous != null) {
      previous.terminate();
    }
  }
}