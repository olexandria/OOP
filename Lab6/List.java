package com.dypko;

public class List  {


    private int currentlength;
    private int [] data;
    private int maxLength;


    public List(int maxLength) {
        if (maxLength > 0) {
            data = new int[maxLength];
            this.maxLength=maxLength;
        }
        else
            System.out.println("Wrong input");
    }

    public void add(int number)
    {
        data[currentlength++]=number;
    }

    public int get(int index)
    {
        if(index>currentlength)
        {
            System.out.println("Wrong index");
            return 0;
        }else
        {
            return data[index];
        }
    }

    public int min()
    {
        int min=1000000000;
        for(int i=0;i<currentlength;i++)
        {
            if(min>data[i])
            {
                min=data[i];
            }
        }
        return min;
    }

    public int max()
    {
        int max=0;
        for(int i=0;i<currentlength;i++)
        {
            if(max<data[i])
            {
                max=data[i];
            }
        }
        return max;
    }

    public double average_value()
    {
        double average=0;
        for(int i=0;i<currentlength;i++)
        {
            average+=data[i];
        }
        average/=currentlength;
        return average;
    }
    public long sum()
    {
        long sum=0;
        for(int i=0;i<currentlength;i++)
        {
            sum+=data[i];
        }
        return sum;
    }


    public int size()
    {
        return currentlength;
    }

    public void random()
    {
        for(int i=0;i<maxLength;i++) {
            add((int) (1+Math.random() * 1000));
        }

    }

}