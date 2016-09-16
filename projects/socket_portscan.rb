#! /usr/bin/ruby
 
require 'socket'
 
$rhost = ARGV[0]
$min_port = ARGV[1]
$max_port = ARGV[2]

begin
	if Integer($min_port) > Integer($max_port)
		puts "\n[!] Invalid Range of Ports"
		puts "usage: ./scan.rb [TARGET] [START PORT] [STOP PORT]"
		exit
	end
rescue ArgumentError
	puts "\n[!] Invalid Range of Ports"
	puts "usage: ./scan.rb [TARGET] [START PORT] [STOP PORT]"
	exit
end

$port_range = (Integer($min_port)..Integer($max_port)).to_a
 
def scan_port(port)
	s = Socket.new Socket::AF_INET, Socket::SOCK_STREAM
	begin
		sockaddr = Socket.pack_sockaddr_in( port, $rhost )
	rescue SocketError
		puts "\n[!] Failed to Resolve Target"
		exit
	end
	begin
		result = s.connect( sockaddr )
	rescue
	end
	if result == 0
		puts "Port #{port}: Open"
	end
	s.close
end
 
puts "\n[*] Beginning Scan... \n\n"
 
$port_range.each do |port|
    scan_port(port)
end
 
puts "\n[*] Scan Complete"
