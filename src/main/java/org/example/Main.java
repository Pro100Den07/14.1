package org.example;

interface Printer {
    void print(MessagePrinter.Message message);
}

// Реалізація інтерфейсу Printer
class MessagePrinter implements Printer {

    @Override
    public void print(Message message) {
        if (message.getText() == null && message.getSender() == null) {
            // Анонімний клас для обробки пустого повідомлення
            EmptyMessageHandler emptyMessageHandler = new EmptyMessageHandler() {
                @Override
                public void handleEmptyMessage() {

                }
            };
            emptyMessageHandler.handleEmptyMessage();
        } else if (message.getSender() == null || message.getSender().isEmpty()) {
            System.out.println("Анонімний користувач відправив повідомлення: " + message.getText());
        } else {
            System.out.println("Користувач " + message.getSender() + " відправив повідомлення: " + message.getText());
        }
    }

    // Статичний внутрішній клас Message
    public static class Message {
        private String text;
        private String sender;

        public Message(String text, String sender) {
            this.text = text;
            this.sender = sender;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }
    }

    // Анонімний клас для обробки пустого повідомлення
    private interface EmptyMessageHandler {
        void handleEmptyMessage();
    }

    private void handleEmptyMessage() {
        System.out.println("Опрацьовується пусте повідомлення від анонімного користувача...");
    }
}

public class Main {
    public static void main(String[] args) {
        MessagePrinter printer = new MessagePrinter();

        // Тестування з різними повідомленнями
        MessagePrinter.Message message1 = new MessagePrinter.Message("Привіт!", "Іван");
        printer.print(message1);

        MessagePrinter.Message message2 = new MessagePrinter.Message("Як справи?", null);
        printer.print(message2);

        MessagePrinter.Message message3 = new MessagePrinter.Message(null, null);
        printer.print(message3);
    }
}


