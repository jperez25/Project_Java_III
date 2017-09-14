class Student
    attr_accessor :FirstName,  :LastName, :PhoneNumber, :Month, :Day
    def initialize
        @FirstName = :FirstName
        @LastName = :LastName
        @PhoneNumber = :PhoneNumber
        @Month = :Month
        @Day = :Day
    end
    def To_String
        @FirstName.to_s+" "+@LastName.to_s+" "+@PhoneNumber.to_s+" "+@Month.to_s+"/"+@Day.to_s
    end
end

End = "end"
FILENAME = "students.txt"

student = Student.new
outFile = File.open FILENAME, 'w+'

print "Enter Student's first name or "+End+" to quit >>"
student.FirstName = gets.chomp

until student.FirstName==End #Loop unnecesary unless requesting multiple students to enter data
    print "Enter last Name >>"
    student.LastName = gets.chomp
    print "Enter phone Number >>"
    student.PhoneNumber = gets.chomp
    print "Enter Birth month >>"
    student.Month = gets.chomp.to_i
    print "Enter Birth Day >>"
    student.Day = gets.chomp.to_i
    outFile.write student.To_String
    print "Enter Student's first name or "+End+" to quit >>"
    student.FirstName = gets.chomp

end
outFile.close