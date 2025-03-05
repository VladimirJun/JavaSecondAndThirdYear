package org.example.task14;

class ExtendedTask extends Task {
    public ExtendedTask(String name) {
        super(name);
    }

    @Override
    public void execute() {
        System.out.println(Thread.currentThread().getName() + " выполняет расширенную задачу: " + super.toString());
        super.execute();
    }
}
