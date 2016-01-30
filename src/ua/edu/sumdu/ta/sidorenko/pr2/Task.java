package ua.edu.sumdu.ta.sidorenko.pr2;

/** Класс описывающей задачу, которая имеет описание и время оповещения о ней */
public class Task {
    public String title;
    public int time, start, end, repeat;
    public boolean active;
    
    /** Метод получения заголовка задачи 
    * @return this.title возвращает заголовок задачи
    */ 
    public String getTitle()
    {
        return this.title;
    }
    
    /** Метод установки заголовка задачи 
    * @param title заголовок задачи 
    */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /** Метод проверки статуса задачи  
    * @return this.active показывает активна ли задача
    */
    public boolean isActive()
    {
        return this.active;
    }
    
    /** Метод установки статуса задачи  
    * @param active переменная показываеющая активная ли задача 
    */
    public void setActive(boolean active)
    {
        this.active = active;
    }
    
    /** Метод задания (время измеряется в секундах, относительно некоторого начала) для единоразовой задачи 
    * @param time время оповещения о задаче  
    */
    public void setTime(int time)
    {
        if (time < 0)
            this.time = 0;
        else
            this.time = time;
    }
    
    /** Метод задания (время измеряется в секундах, относительно некоторого начала) для повторяющейся задачи 
    * @param start время начала оповещения о задаче 
    * @param end время конца оповещения о задаче 
    * @param repeat интервал времени, через который необходимо повторить оповещение о задаче
    */
    public void setTime(int start, int end, int repeat)
    {
        if (start > 0 && end > 0 && repeat > 0)
        {
            this.time = start;
            this.start = start;
            this.end = end;
            this.repeat = repeat;
        }
        else if ((start < 0 && end > 0 && repeat > 0) || (start < 0 && end < 0 && repeat < 0))
        {
            this.time = 0;
            this.start = 0;
            this.end = 0;
            this.repeat = 0;
        }
        else if ((start > 0 && end < 0 && repeat > 0) || (start > 0 && end > 0 && repeat < 0))
        {
            this.time = start;
            this.start = start;
            this.end = start;
            this.repeat = 0;
        }
    }
    
    /** Метод получения время первого (или единственного) оповещения для единоразовой задачи
    * @return this.time возвращает время задачи
    */
    public int getTime()
    {
        if (isRepeated())
            return this.time = this.start;
        else
            return this.time;
    }
    
    /** Метод получения времени начала события, если задача не повторяется, то просто время события 
    * @return this.start возвращает если задача повторяется
    * @return this.time возвращает если задача не повторяется
    */
    public int getStartTime()
    {
        if (isRepeated())
            return this.start;
        else
            return this.time;
    }
    
    /** Метод получения времени конца события, если задача не повторяется, то просто время события 
    * @return this.end возвращает если задача повторяется
    * @return this.time возвращает если задача не повторяется
    */
    public int getEndTime()
    {
        if (isRepeated())
            return this.end;
        else
            return this.time;
    }
    
    /** Метод получения промежутка повторени, если задача не повторяется, то просто 0 
    * @return this.repeat возвращает если задача повторяется
    * @return 0 возвращает если задача не повторяется
    */
    public int getRepeatInterval()
    {
        if (isRepeated())
            return this.repeat;
        else
            return 0;
    }
    
    /** Метод проверки повторения события 
    * @return true возвращает если задача повторяется
    * @return false возвращает если задача не повторяется
    */
    public boolean isRepeated()
    {
        return this.repeat > 0;            
    }
    
    /** Метод, возвращающий описание данной задачи */
    public String toString()
    {
        if (!isActive())
            return String.format("Task \"%s\" is inactive", this.title);
        else if (isActive() == true && !isRepeated())
            return String.format("Task \"%s\" at %d", this.title, this.time);
        else if (isActive() == true && isRepeated() == true)
            return String.format("Task \"%s\" from %d to %d every %d seconds", this.title, this.start, this.end, this.repeat);
        else
            return String.format("Task \"%s\" is inactive", this.title);
    }
    
    /** Метод, возвращающий время следующего оповещения, после указанного времени  
    * @return -1 если после указанного времени событий больше нет или задача неактивна
    */
    public int nextTimeAfter(int time)
    {
        int nextTimeAfter = 0;
        if (isActive() == true && !isRepeated() && time >= 0 && time < this.time)
            return this.time;
        else if (isActive() == true && isRepeated() == true && time < this.start && time >= 0)
            return this.start;
        else if (isActive() == true && isRepeated() == true && time >= this.time && time < this.time + this.repeat)
            return this.time + this.repeat;
        
        else if (isActive() == true && isRepeated() == true && time >= this.start + this.repeat && time < this.end - this.repeat)
        {
            for (int i = 1; i <= this.repeat; i++)
            {
                if (time >= this.start + this.repeat * i && time < this.start + this.repeat * (i + 1))
                {
                    nextTimeAfter = this.start + (i + 1) * this.repeat;
                }
            }
            return nextTimeAfter;
        }
        else
            return -1;
    }
    
    /** Конструктор 
    * @param title заголовок задачи 
    * @param time время оповещения о задаче
    */
    public Task (String title, int time)
    {
        setTitle(title);
        setTime(time);
    }
    
    /** Конструктор 
    * @param title заголовок задачи 
    * @param start время начала оповещения о задаче 
    * @param end время конца оповещения о задаче
    * @param repeat интервал времени, через который необходимо повторить оповещение о задаче
    */
    public Task (String title, int start, int end, int repeat)
    {
        setTitle(title);
        setTime(start, end, repeat);
    }
}
