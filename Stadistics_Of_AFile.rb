print "Enter file name: "
file_name = gets.chomp

if File.exist? file_name
    puts "Fileexist"
    puts "File was created "+File.ctime(file_name).to_s
    puts "File was last accesed "+File.atime(file_name).to_s
    puts "File was last written to "+File.mtime(file_name).to_s
else
    puts "File does not exist"
end