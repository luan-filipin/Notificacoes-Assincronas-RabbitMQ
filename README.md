# 📬 Sistema de Notificações com Spring Boot, RabbitMQ e Mailtrap

Este é um projeto simples que simula um sistema de envio de notificações por e-mail utilizando **Spring Boot**, **RabbitMQ** e **Mailtrap**. O objetivo é demonstrar como integrar filas de mensagens com serviços assíncronos, como o envio de e-mails.

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring AMQP (RabbitMQ)
- Spring Mail
- RabbitMQ (mensageria)
- Mailtrap (simulação de envio de e-mails)
- Maven

## 🧪 Como Funciona

1. A aplicação expõe um endpoint REST que recebe uma notificação via JSON.
2. Essa notificação é enviada para uma **fila RabbitMQ**.
3. Um listener consome essa fila e chama o serviço de e-mail.
4. O e-mail é enviado via **SMTP Mailtrap** (simulado).
