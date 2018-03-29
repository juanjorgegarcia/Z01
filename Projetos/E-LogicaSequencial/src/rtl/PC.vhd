-- Elementos de Sistemas
-- developed by Luciano Soares
-- file: PC.vhd
-- date: 4/4/2017

-- Contador de 16bits
-- if (reset[t] == 1) out[t+1] = 0
-- else if (load[t] == 1)  out[t+1] = in[t]
-- else if (inc[t] == 1) out[t+1] = out[t] + 1
-- else out[t+1] = out[t]

library ieee;
use ieee.std_logic_1164.all;
use IEEE.NUMERIC_STD.ALL;

entity PC is
    port(
        clock     : in  STD_LOGIC;
        increment : in  STD_LOGIC;
        load      : in  STD_LOGIC;
        reset     : in  STD_LOGIC;
        input     : in  STD_LOGIC_VECTOR(15 downto 0);
        output    : out STD_LOGIC_VECTOR(15 downto 0)
    );
end entity;

architecture arch of PC is

component Inc16 is
	port(
		a   :  in STD_LOGIC_VECTOR(15 downto 0);
		q   : out STD_LOGIC_VECTOR(15 downto 0)
	);
end component;

 signal tMenos, incOut : STD_LOGIC_VECTOR(15 downto 0);


begin

	Inc0 : Inc16 port map (tMenos,incOut);
	
	
	process(clock, increment, load, reset) is
		begin
			if (reset = '1') then
				output <= tMenos & '0';
			elsif (load = '1') then 
				output <= input; --v
			elsif (increment = '1') then
				output <= incOut;
			else
				output <= output;
			end if;
			
	end process;

end architecture;
