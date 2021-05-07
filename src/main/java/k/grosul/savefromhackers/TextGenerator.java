package k.grosul.savefromhackers;

import  java.util.Random;


public class TextGenerator {
    private final Random random = new Random();

    public String generate(User user) {
        int number = random.nextInt(10) + 2;
        String result = String.format(
                "Уважаемый %s %s года рождения, проживающий по адресу %s, паспорт %s выдан %s, спасибо, что " +
                        "воспользовались нашим сервсисом! Вашы данные были обнаружены в %d мошеннеческих базах данных, " +
                        "но мы всё удалили. Не забудьте рассказать о нас вашим близким, безопасность в сети - это важно!",
                user.getName(),
                user.getBirth(),
                user.getPassportRegistration(),
                user.getPassportNumber(),
                user.getPassportGiven(),
                number);
        return result;
    }


}
