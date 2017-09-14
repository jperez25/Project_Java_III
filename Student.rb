class Student
    #class variable
    @@Number_Of_Students = 0
    #Constructor
    def initialize(id,fn,ln,adr)
        #instance variables
        @student_ID = id
        @First_Name = fn
        @Last_Name = ln
        @Address = adr
    end
    #accessor methods
    def getId
        @student_ID
    end
    def getFname
        return @First_Name #return statement is unnecesary, here just to avoid confusion
    end
    def getLname
        return @Last_Name
    end
    def getAddress
        return @Address        
    end
    #setter method
    def setFname=(fn)
        @First_Name = fn
    end
    #To string method
    def to_str
        return "Id: #{@student_ID}  First Name: #{@First_Name} Last Name: #{@Last_Name} Address:#{@Address}"
    end  
end

students = [] #Array of objects
for x in 0..2 do #use range to loop
    puts "Please fill the next information for student #{x+1}"
    print "Enter Id: " 
    id = gets.chomp #Get data from user to fill all data fields for the objects
    print "Enter First Name: " 
    fn = gets.chomp
    print "Enter Last Name: " 
    ln = gets.chomp
    print "Enter Address: " 
    adr = gets.chomp
    students[x] = Student.new(id,fn,ln,adr)
end

for e in 1..students.size do
    puts students[e-1].to_str
end