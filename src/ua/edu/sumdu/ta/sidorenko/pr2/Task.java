package ua.edu.sumdu.ta.sidorenko.pr2;

/** ����� ����������� ������, ������� ����� �������� � ����� ���������� � ��� */
public class Task {
    public String title;
    public int time, start, end, repeat;
    public boolean active;
    
    /** ����� ��������� ��������� ������ 
    * @return this.title ���������� ��������� ������
    */ 
    public String getTitle()
    {
        return this.title;
    }
    
    /** ����� ��������� ��������� ������ 
    * @param title ��������� ������ 
    */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /** ����� �������� ������� ������  
    * @return this.active ���������� ������� �� ������
    */
    public boolean isActive()
    {
        return this.active;
    }
    
    /** ����� ��������� ������� ������  
    * @param active ���������� ������������� �������� �� ������ 
    */
    public void setActive(boolean active)
    {
        this.active = active;
    }
    
    /** ����� ������� (����� ���������� � ��������, ������������ ���������� ������) ��� ������������ ������ 
    * @param time ����� ���������� � ������  
    */
    public void setTime(int time)
    {
        if (time < 0)
            this.time = 0;
        else
            this.time = time;
    }
    
    /** ����� ������� (����� ���������� � ��������, ������������ ���������� ������) ��� ������������� ������ 
    * @param start ����� ������ ���������� � ������ 
    * @param end ����� ����� ���������� � ������ 
    * @param repeat �������� �������, ����� ������� ���������� ��������� ���������� � ������
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
    
    /** ����� ��������� ����� ������� (��� �������������) ���������� ��� ������������ ������
    * @return this.time ���������� ����� ������
    */
    public int getTime()
    {
        if (isRepeated())
            return this.time = this.start;
        else
            return this.time;
    }
    
    /** ����� ��������� ������� ������ �������, ���� ������ �� �����������, �� ������ ����� ������� 
    * @return this.start ���������� ���� ������ �����������
    * @return this.time ���������� ���� ������ �� �����������
    */
    public int getStartTime()
    {
        if (isRepeated())
            return this.start;
        else
            return this.time;
    }
    
    /** ����� ��������� ������� ����� �������, ���� ������ �� �����������, �� ������ ����� ������� 
    * @return this.end ���������� ���� ������ �����������
    * @return this.time ���������� ���� ������ �� �����������
    */
    public int getEndTime()
    {
        if (isRepeated())
            return this.end;
        else
            return this.time;
    }
    
    /** ����� ��������� ���������� ���������, ���� ������ �� �����������, �� ������ 0 
    * @return this.repeat ���������� ���� ������ �����������
    * @return 0 ���������� ���� ������ �� �����������
    */
    public int getRepeatInterval()
    {
        if (isRepeated())
            return this.repeat;
        else
            return 0;
    }
    
    /** ����� �������� ���������� ������� 
    * @return true ���������� ���� ������ �����������
    * @return false ���������� ���� ������ �� �����������
    */
    public boolean isRepeated()
    {
        return this.repeat > 0;            
    }
    
    /** �����, ������������ �������� ������ ������ */
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
    
    /** �����, ������������ ����� ���������� ����������, ����� ���������� �������  
    * @return -1 ���� ����� ���������� ������� ������� ������ ��� ��� ������ ���������
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
    
    /** ����������� 
    * @param title ��������� ������ 
    * @param time ����� ���������� � ������
    */
    public Task (String title, int time)
    {
        setTitle(title);
        setTime(time);
    }
    
    /** ����������� 
    * @param title ��������� ������ 
    * @param start ����� ������ ���������� � ������ 
    * @param end ����� ����� ���������� � ������
    * @param repeat �������� �������, ����� ������� ���������� ��������� ���������� � ������
    */
    public Task (String title, int start, int end, int repeat)
    {
        setTitle(title);
        setTime(start, end, repeat);
    }
}
