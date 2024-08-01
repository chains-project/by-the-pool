# gumtree-jvm-bytecode-diff
> todo: cool name :P

Tool that reports fine-grained diff between two Java classfiles.
The diff is based on the [gumtree](https://github.com/GumTreeDiff/gumtree) 
algorithm, which is a tree differencing tool.
The tree representation for the bytecode is based on the [`ClassModel`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/lang/classfile/ClassModel.html)
API proposed in [JEP 457](https://openjdk.org/jeps/457).
As of writing this README, this feature is JDK 22 as first preview.

## Examples

1. Simplifying decompiled diff
    #### With diffoscope 272
    > Java 17
    
    ```diff
    --- A45305b1a-3bdc-455f-b22d-96636743c129.class
    +++ A4e6577e8-d1ee-4acd-b38f-1dcd68658a48.class
      ├── procyon -ec {}
      │ @@ -1,9 +1,9 @@
      │  
      │  package io.dropwizard.jersey;
      │  
    - │ public class DropwizardResourceConfig$SpecificBindere75da6ac-3201-4073-bd4f-75682c761862 extends DropwizardResourceConfig$SpecificBinder
    + │ public class DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443 extends DropwizardResourceConfig$SpecificBinder
      │  {
    - │    public DropwizardResourceConfig$SpecificBindere75da6ac-3201-4073-bd4f-75682c761862(final Object o, final Class clazz) {
    + │    public DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443(final Object o, final Class clazz) {
      │          super(o, clazz);
      │      }
      │  }
    ```
    
    #### With gumtree-jvm-bytecode-diff
    > Java 22
    
    ```txt
    ===
    update-node
    ---
    thisClass: io/dropwizard/jersey/DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443 [0,0]
    replace io/dropwizard/jersey/DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443 by io/dropwizard/jersey/DropwizardResourceConfig$SpecificBindere75da6ac-3201-4073-bd4f-75682c761862
    
    ===
    update-node
    ---
    sourceFileAttribute: DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443.java [0,0]
    replace DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443.java by DropwizardResourceConfig$SpecificBindere75da6ac-3201-4073-bd4f-75682c761862.java
    ```
    
    It precisely tells you what has changed in the classfile. In this case,
    `thisClass` and `sourceFileAttribute` have been updated.

2. Simplifying disassembled diff
    
    <details>
        <summary>
            <h4> With diffoscope 272</h4>
            <blockquote>Java 22</blockquote>
        </summary>
    <pre>
    --- src/test/resources/classfileVersion/ClientMain6.class
    +++ src/test/resources/classfileVersion/ClientMain8.class
    ├── javap -verbose -constants -s -l -private {}
    │ @@ -1,339 +1,332 @@
    │ -  SHA-256 checksum 4509fdd7e1698dbbe4fa37122e86b2e1ac477a2c926eccfcba15db93fb0b6e47
    │ +  SHA-256 checksum 426d165c027ad3388b00f9ef08750cfa3802ab90c20e24f9ad5a4031e77e90df
    │    Compiled from "ClientMain.java"
    │  public class com.turn.ttorrent.cli.ClientMain
    │    minor version: 0
    │ -  major version: 50
    │ +  major version: 52
    │    flags: (0x0021) ACC_PUBLIC, ACC_SUPER
    │ -  this_class: #50                         // com/turn/ttorrent/cli/ClientMain
    │ -  super_class: #85                        // java/lang/Object
    │ +  this_class: #124                        // com/turn/ttorrent/cli/ClientMain
    │ +  super_class: #2                         // java/lang/Object
    │    interfaces: 0, fields: 2, methods: 5, attributes: 2
    │  Constant pool:
    │ -    #1 = Methodref          #85.#157      // java/lang/Object."<init>":()V
    │ -    #2 = Methodref          #158.#159     // java/net/NetworkInterface.getByName:(Ljava/lang/String;)Ljava/net/NetworkInterface;
    │ -    #3 = Methodref          #158.#160     // java/net/NetworkInterface.getInetAddresses:()Ljava/util/Enumeration;
    │ -    #4 = InterfaceMethodref #161.#162     // java/util/Enumeration.hasMoreElements:()Z
    │ -    #5 = InterfaceMethodref #161.#163     // java/util/Enumeration.nextElement:()Ljava/lang/Object;
    │ -    #6 = Class              #164          // java/net/InetAddress
    │ -    #7 = Class              #165          // java/net/Inet4Address
    │ -    #8 = Methodref          #6.#166       // java/net/InetAddress.getLocalHost:()Ljava/net/InetAddress;
    │ -    #9 = Class              #167          // java/nio/channels/UnsupportedAddressTypeException
    │ -   #10 = Methodref          #9.#157       // java/nio/channels/UnsupportedAddressTypeException."<init>":()V
    │ -   #11 = String             #168          // usage: Client [options] <torrent>
    │ -   #12 = Methodref          #169.#170     // java/io/PrintStream.println:(Ljava/lang/String;)V
    │ -   #13 = Methodref          #169.#171     // java/io/PrintStream.println:()V
    │ -   #14 = String             #172          // Available options:
    │ -   #15 = String             #173          //   -h,--help                  Show this help and exit.
    │ -   #16 = String             #174          //   -o,--output DIR            Read/write data to directory DIR.
    │ -   #17 = String             #175          //   -i,--iface IFACE           Bind to interface IFACE.
    │ -   #18 = String             #176          //   -s,--seed SECONDS          Time to seed after downloading (default: infinitely).
    │ -   #19 = String             #177          //   -d,--max-download KB/SEC   Max download rate (default: unlimited).
    │ -   #20 = String             #178          //   -u,--max-upload KB/SEC     Max upload rate (default: unlimited).
    │ -   #21 = Class              #179          // org/apache/log4j/ConsoleAppender
    │ -   #22 = Class              #180          // org/apache/log4j/PatternLayout
    │ -   #23 = String             #181          // %d [%-25t] %-5p: %m%n
    │ -   #24 = Methodref          #22.#182      // org/apache/log4j/PatternLayout."<init>":(Ljava/lang/String;)V
    │ -   #25 = Methodref          #21.#183      // org/apache/log4j/ConsoleAppender."<init>":(Lorg/apache/log4j/Layout;)V
    │ -   #26 = Methodref          #184.#185     // org/apache/log4j/BasicConfigurator.configure:(Lorg/apache/log4j/Appender;)V
    │ -   #27 = Class              #186          // jargs/gnu/CmdLineParser
    │ -   #28 = Methodref          #27.#157      // jargs/gnu/CmdLineParser."<init>":()V
    │ -   #29 = String             #132          // help
    │ -   #30 = Methodref          #27.#187      // jargs/gnu/CmdLineParser.addBooleanOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ -   #31 = String             #136          // output
    │ -   #32 = Methodref          #27.#188      // jargs/gnu/CmdLineParser.addStringOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ -   #33 = String             #104          // iface
    │ -   #34 = String             #189          // seed
    │ -   #35 = Methodref          #27.#190      // jargs/gnu/CmdLineParser.addIntegerOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ -   #36 = String             #191          // max-upload
    │ -   #37 = Methodref          #27.#192      // jargs/gnu/CmdLineParser.addDoubleOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ -   #38 = String             #193          // max-download
    │ -   #39 = Methodref          #27.#194      // jargs/gnu/CmdLineParser.parse:([Ljava/lang/String;)V
    │ -   #40 = Class              #195          // jargs/gnu/CmdLineParser$OptionException
    │ -   #41 = Fieldref           #196.#197     // java/lang/System.err:Ljava/io/PrintStream;
    │ -   #42 = Methodref          #40.#198      // jargs/gnu/CmdLineParser$OptionException.getMessage:()Ljava/lang/String;
    │ -   #43 = Methodref          #50.#199      // com/turn/ttorrent/cli/ClientMain.usage:(Ljava/io/PrintStream;)V
    │ -   #44 = Methodref          #196.#200     // java/lang/System.exit:(I)V
    │ -   #45 = Fieldref           #47.#201      // java/lang/Boolean.TRUE:Ljava/lang/Boolean;
    │ -   #46 = Methodref          #27.#202      // jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;)Ljava/lang/Object;
    │ -   #47 = Class              #203          // java/lang/Boolean
    │ -   #48 = Methodref          #47.#204      // java/lang/Boolean.equals:(Ljava/lang/Object;)Z
    │ -   #49 = Fieldref           #196.#205     // java/lang/System.out:Ljava/io/PrintStream;
    │ -   #50 = Class              #206          // com/turn/ttorrent/cli/ClientMain
    │ -   #51 = String             #207          // /tmp
    │ -   #52 = Methodref          #27.#208      // jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ -   #53 = Class              #209          // java/lang/String
    │ -   #54 = Methodref          #55.#210      // java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
    │ -   #55 = Class              #211          // java/lang/Integer
    │ -   #56 = Methodref          #55.#212      // java/lang/Integer.intValue:()I
    │ -   #57 = Methodref          #58.#213      // java/lang/Double.valueOf:(D)Ljava/lang/Double;
    │ -   #58 = Class              #214          // java/lang/Double
    │ -   #59 = Methodref          #58.#215      // java/lang/Double.doubleValue:()D
    │ -   #60 = Methodref          #27.#216      // jargs/gnu/CmdLineParser.getRemainingArgs:()[Ljava/lang/String;
    │ -   #61 = Class              #217          // com/turn/ttorrent/client/Client
    │ -   #62 = Methodref          #50.#218      // com/turn/ttorrent/cli/ClientMain.getIPv4Address:(Ljava/lang/String;)Ljava/net/Inet4Address;
    │ -   #63 = Class              #219          // java/io/File
    │ -   #64 = Methodref          #63.#182      // java/io/File."<init>":(Ljava/lang/String;)V
    │ -   #65 = Methodref          #220.#221     // com/turn/ttorrent/client/SharedTorrent.fromFile:(Ljava/io/File;Ljava/io/File;)Lcom/turn/ttorrent/client/SharedTorrent;
    │ -   #66 = Methodref          #61.#222      // com/turn/ttorrent/client/Client."<init>":(Ljava/net/InetAddress;Lcom/turn/ttorrent/client/SharedTorrent;)V
    │ -   #67 = Methodref          #61.#223      // com/turn/ttorrent/client/Client.setMaxDownloadRate:(D)V
    │ -   #68 = Methodref          #61.#224      // com/turn/ttorrent/client/Client.setMaxUploadRate:(D)V
    │ -   #69 = Methodref          #225.#226     // java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
    │ -   #70 = Class              #227          // java/lang/Thread
    │ -   #71 = Class              #228          // com/turn/ttorrent/client/Client$ClientShutdown
    │ -   #72 = Methodref          #71.#230      // com/turn/ttorrent/client/Client$ClientShutdown."<init>":(Lcom/turn/ttorrent/client/Client;Ljava/util/Timer;)V
    │ -   #73 = Methodref          #70.#231      // java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
    │ -   #74 = Methodref          #225.#232     // java/lang/Runtime.addShutdownHook:(Ljava/lang/Thread;)V
    │ -   #75 = Methodref          #61.#233      // com/turn/ttorrent/client/Client.share:(I)V
    │ -   #76 = Fieldref           #234.#235     // com/turn/ttorrent/client/Client$ClientState.ERROR:Lcom/turn/ttorrent/client/Client$ClientState;
    │ -   #77 = Methodref          #61.#236      // com/turn/ttorrent/client/Client.getState:()Lcom/turn/ttorrent/client/Client$ClientState;
    │ -   #78 = Methodref          #234.#204     // com/turn/ttorrent/client/Client$ClientState.equals:(Ljava/lang/Object;)Z
    │ -   #79 = Class              #237          // java/lang/Exception
    │ -   #80 = Fieldref           #50.#238      // com/turn/ttorrent/cli/ClientMain.logger:Lorg/slf4j/Logger;
    │ -   #81 = String             #239          // Fatal error: {}
    │ -   #82 = Methodref          #79.#198      // java/lang/Exception.getMessage:()Ljava/lang/String;
    │ -   #83 = InterfaceMethodref #240.#241     // org/slf4j/Logger.error:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    │ -   #84 = Methodref          #242.#243     // org/slf4j/LoggerFactory.getLogger:(Ljava/lang/Class;)Lorg/slf4j/Logger;
    │ -   #85 = Class              #244          // java/lang/Object
    │ -   #86 = Utf8               logger
    │ -   #87 = Utf8               Lorg/slf4j/Logger;
    │ -   #88 = Utf8               DEFAULT_OUTPUT_DIRECTORY
    │ -   #89 = Utf8               Ljava/lang/String;
    │ -   #90 = Utf8               ConstantValue
    │ -   #91 = Utf8               <init>
    │ -   #92 = Utf8               ()V
    │ -   #93 = Utf8               Code
    │ -   #94 = Utf8               LineNumberTable
    │ -   #95 = Utf8               LocalVariableTable
    │ -   #96 = Utf8               this
    │ -   #97 = Utf8               Lcom/turn/ttorrent/cli/ClientMain;
    │ -   #98 = Utf8               getIPv4Address
    │ -   #99 = Utf8               (Ljava/lang/String;)Ljava/net/Inet4Address;
    │ -  #100 = Utf8               addr
    │ -  #101 = Utf8               Ljava/net/InetAddress;
    │ -  #102 = Utf8               addresses
    │ -  #103 = Utf8               Ljava/util/Enumeration;
    │ -  #104 = Utf8               iface
    │ -  #105 = Utf8               localhost
    │ -  #106 = Utf8               LocalVariableTypeTable
    │ -  #107 = Utf8               Ljava/util/Enumeration<Ljava/net/InetAddress;>;
    │ -  #108 = Utf8               StackMapTable
    │ -  #109 = Class              #245          // java/util/Enumeration
    │ -  #110 = Class              #164          // java/net/InetAddress
    │ -  #111 = Utf8               Exceptions
    │ -  #112 = Class              #246          // java/net/SocketException
    │ -  #113 = Class              #247          // java/net/UnknownHostException
    │ -  #114 = Utf8               usage
    │ -  #115 = Utf8               (Ljava/io/PrintStream;)V
    │ -  #116 = Utf8               s
    │ -  #117 = Utf8               Ljava/io/PrintStream;
    │ -  #118 = Utf8               main
    │ -  #119 = Utf8               ([Ljava/lang/String;)V
    │ -  #120 = Utf8               oe
    │ -  #121 = Utf8               OptionException
    │ -  #122 = Utf8               InnerClasses
    │ -  #123 = Utf8               Ljargs/gnu/CmdLineParser$OptionException;
    │ -  #124 = Utf8               c
    │ -  #125 = Utf8               Lcom/turn/ttorrent/client/Client;
    │ -  #126 = Utf8               e
    │ -  #127 = Utf8               Ljava/lang/Exception;
    │ -  #128 = Utf8               args
    │ -  #129 = Utf8               [Ljava/lang/String;
    │ -  #130 = Utf8               parser
    │ -  #131 = Utf8               Ljargs/gnu/CmdLineParser;
    │ -  #132 = Utf8               help
    │ -  #133 = Class              #248          // jargs/gnu/CmdLineParser$Option
    │ -  #134 = Utf8               Option
    │ -  #135 = Utf8               Ljargs/gnu/CmdLineParser$Option;
    │ -  #136 = Utf8               output
    │ -  #137 = Utf8               seedTime
    │ -  #138 = Utf8               maxUpload
    │ -  #139 = Utf8               maxDownload
    │ -  #140 = Utf8               outputValue
    │ -  #141 = Utf8               ifaceValue
    │ -  #142 = Utf8               seedTimeValue
    │ -  #143 = Utf8               I
    │ -  #144 = Utf8               maxDownloadRate
    │ -  #145 = Utf8               D
    │ -  #146 = Utf8               maxUploadRate
    │ -  #147 = Utf8               otherArgs
    │ -  #148 = Class              #129          // "[Ljava/lang/String;"
    │ -  #149 = Class              #186          // jargs/gnu/CmdLineParser
    │ -  #150 = Class              #248          // jargs/gnu/CmdLineParser$Option
    │ -  #151 = Class              #195          // jargs/gnu/CmdLineParser$OptionException
    │ -  #152 = Class              #209          // java/lang/String
    │ -  #153 = Class              #237          // java/lang/Exception
    │ -  #154 = Utf8               <clinit>
    │ -  #155 = Utf8               SourceFile
    │ -  #156 = Utf8               ClientMain.java
    │ -  #157 = NameAndType        #91:#92       // "<init>":()V
    │ -  #158 = Class              #249          // java/net/NetworkInterface
    │ -  #159 = NameAndType        #250:#251     // getByName:(Ljava/lang/String;)Ljava/net/NetworkInterface;
    │ -  #160 = NameAndType        #252:#253     // getInetAddresses:()Ljava/util/Enumeration;
    │ -  #161 = Class              #245          // java/util/Enumeration
    │ -  #162 = NameAndType        #254:#255     // hasMoreElements:()Z
    │ -  #163 = NameAndType        #256:#257     // nextElement:()Ljava/lang/Object;
    │ -  #164 = Utf8               java/net/InetAddress
    │ -  #165 = Utf8               java/net/Inet4Address
    │ -  #166 = NameAndType        #258:#259     // getLocalHost:()Ljava/net/InetAddress;
    │ -  #167 = Utf8               java/nio/channels/UnsupportedAddressTypeException
    │ -  #168 = Utf8               usage: Client [options] <torrent>
    │ -  #169 = Class              #260          // java/io/PrintStream
    │ -  #170 = NameAndType        #261:#262     // println:(Ljava/lang/String;)V
    │ -  #171 = NameAndType        #261:#92      // println:()V
    │ -  #172 = Utf8               Available options:
    │ -  #173 = Utf8                 -h,--help                  Show this help and exit.
    │ -  #174 = Utf8                 -o,--output DIR            Read/write data to directory DIR.
    │ -  #175 = Utf8                 -i,--iface IFACE           Bind to interface IFACE.
    │ -  #176 = Utf8                 -s,--seed SECONDS          Time to seed after downloading (default: infinitely).
    │ -  #177 = Utf8                 -d,--max-download KB/SEC   Max download rate (default: unlimited).
    │ -  #178 = Utf8                 -u,--max-upload KB/SEC     Max upload rate (default: unlimited).
    │ -  #179 = Utf8               org/apache/log4j/ConsoleAppender
    │ -  #180 = Utf8               org/apache/log4j/PatternLayout
    │ -  #181 = Utf8               %d [%-25t] %-5p: %m%n
    │ -  #182 = NameAndType        #91:#262      // "<init>":(Ljava/lang/String;)V
    │ -  #183 = NameAndType        #91:#263      // "<init>":(Lorg/apache/log4j/Layout;)V
    │ -  #184 = Class              #264          // org/apache/log4j/BasicConfigurator
    │ -  #185 = NameAndType        #265:#266     // configure:(Lorg/apache/log4j/Appender;)V
    │ -  #186 = Utf8               jargs/gnu/CmdLineParser
    │ -  #187 = NameAndType        #267:#268     // addBooleanOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ -  #188 = NameAndType        #269:#268     // addStringOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ -  #189 = Utf8               seed
    │ -  #190 = NameAndType        #270:#268     // addIntegerOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ -  #191 = Utf8               max-upload
    │ -  #192 = NameAndType        #271:#268     // addDoubleOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ -  #193 = Utf8               max-download
    │ -  #194 = NameAndType        #272:#119     // parse:([Ljava/lang/String;)V
    │ -  #195 = Utf8               jargs/gnu/CmdLineParser$OptionException
    │ -  #196 = Class              #273          // java/lang/System
    │ -  #197 = NameAndType        #274:#117     // err:Ljava/io/PrintStream;
    │ -  #198 = NameAndType        #275:#276     // getMessage:()Ljava/lang/String;
    │ -  #199 = NameAndType        #114:#115     // usage:(Ljava/io/PrintStream;)V
    │ -  #200 = NameAndType        #277:#278     // exit:(I)V
    │ -  #201 = NameAndType        #279:#280     // TRUE:Ljava/lang/Boolean;
    │ -  #202 = NameAndType        #281:#282     // getOptionValue:(Ljargs/gnu/CmdLineParser$Option;)Ljava/lang/Object;
    │ -  #203 = Utf8               java/lang/Boolean
    │ -  #204 = NameAndType        #283:#284     // equals:(Ljava/lang/Object;)Z
    │ -  #205 = NameAndType        #285:#117     // out:Ljava/io/PrintStream;
    │ -  #206 = Utf8               com/turn/ttorrent/cli/ClientMain
    │ -  #207 = Utf8               /tmp
    │ -  #208 = NameAndType        #281:#286     // getOptionValue:(Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ -  #209 = Utf8               java/lang/String
    │ -  #210 = NameAndType        #287:#288     // valueOf:(I)Ljava/lang/Integer;
    │ -  #211 = Utf8               java/lang/Integer
    │ -  #212 = NameAndType        #289:#290     // intValue:()I
    │ -  #213 = NameAndType        #287:#291     // valueOf:(D)Ljava/lang/Double;
    │ -  #214 = Utf8               java/lang/Double
    │ -  #215 = NameAndType        #292:#293     // doubleValue:()D
    │ -  #216 = NameAndType        #294:#295     // getRemainingArgs:()[Ljava/lang/String;
    │ -  #217 = Utf8               com/turn/ttorrent/client/Client
    │ -  #218 = NameAndType        #98:#99       // getIPv4Address:(Ljava/lang/String;)Ljava/net/Inet4Address;
    │ -  #219 = Utf8               java/io/File
    │ -  #220 = Class              #296          // com/turn/ttorrent/client/SharedTorrent
    │ -  #221 = NameAndType        #297:#298     // fromFile:(Ljava/io/File;Ljava/io/File;)Lcom/turn/ttorrent/client/SharedTorrent;
    │ -  #222 = NameAndType        #91:#299      // "<init>":(Ljava/net/InetAddress;Lcom/turn/ttorrent/client/SharedTorrent;)V
    │ -  #223 = NameAndType        #300:#301     // setMaxDownloadRate:(D)V
    │ -  #224 = NameAndType        #302:#301     // setMaxUploadRate:(D)V
    │ -  #225 = Class              #303          // java/lang/Runtime
    │ -  #226 = NameAndType        #304:#305     // getRuntime:()Ljava/lang/Runtime;
    │ -  #227 = Utf8               java/lang/Thread
    │ -  #228 = Utf8               com/turn/ttorrent/client/Client$ClientShutdown
    │ -  #229 = Utf8               ClientShutdown
    │ -  #230 = NameAndType        #91:#306      // "<init>":(Lcom/turn/ttorrent/client/Client;Ljava/util/Timer;)V
    │ -  #231 = NameAndType        #91:#307      // "<init>":(Ljava/lang/Runnable;)V
    │ -  #232 = NameAndType        #308:#309     // addShutdownHook:(Ljava/lang/Thread;)V
    │ -  #233 = NameAndType        #310:#278     // share:(I)V
    │ -  #234 = Class              #311          // com/turn/ttorrent/client/Client$ClientState
    │ -  #235 = NameAndType        #313:#314     // ERROR:Lcom/turn/ttorrent/client/Client$ClientState;
    │ -  #236 = NameAndType        #315:#316     // getState:()Lcom/turn/ttorrent/client/Client$ClientState;
    │ -  #237 = Utf8               java/lang/Exception
    │ -  #238 = NameAndType        #86:#87       // logger:Lorg/slf4j/Logger;
    │ -  #239 = Utf8               Fatal error: {}
    │ -  #240 = Class              #317          // org/slf4j/Logger
    │ -  #241 = NameAndType        #318:#319     // error:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    │ -  #242 = Class              #320          // org/slf4j/LoggerFactory
    │ -  #243 = NameAndType        #321:#322     // getLogger:(Ljava/lang/Class;)Lorg/slf4j/Logger;
    │ -  #244 = Utf8               java/lang/Object
    │ -  #245 = Utf8               java/util/Enumeration
    │ -  #246 = Utf8               java/net/SocketException
    │ -  #247 = Utf8               java/net/UnknownHostException
    │ -  #248 = Utf8               jargs/gnu/CmdLineParser$Option
    │ -  #249 = Utf8               java/net/NetworkInterface
    │ -  #250 = Utf8               getByName
    │ -  #251 = Utf8               (Ljava/lang/String;)Ljava/net/NetworkInterface;
    │ -  #252 = Utf8               getInetAddresses
    │ -  #253 = Utf8               ()Ljava/util/Enumeration;
    │ -  #254 = Utf8               hasMoreElements
    │ -  #255 = Utf8               ()Z
    │ -  #256 = Utf8               nextElement
    │ -  #257 = Utf8               ()Ljava/lang/Object;
    │ -  #258 = Utf8               getLocalHost
    │ -  #259 = Utf8               ()Ljava/net/InetAddress;
    │ -  #260 = Utf8               java/io/PrintStream
    │ -  #261 = Utf8               println
    │ -  #262 = Utf8               (Ljava/lang/String;)V
    │ -  #263 = Utf8               (Lorg/apache/log4j/Layout;)V
    │ -  #264 = Utf8               org/apache/log4j/BasicConfigurator
    │ -  #265 = Utf8               configure
    │ -  #266 = Utf8               (Lorg/apache/log4j/Appender;)V
    │ -  #267 = Utf8               addBooleanOption
    │ -  #268 = Utf8               (CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ -  #269 = Utf8               addStringOption
    │ -  #270 = Utf8               addIntegerOption
    │ -  #271 = Utf8               addDoubleOption
    │ -  #272 = Utf8               parse
    │ -  #273 = Utf8               java/lang/System
    │ -  #274 = Utf8               err
    │ -  #275 = Utf8               getMessage
    │ -  #276 = Utf8               ()Ljava/lang/String;
    │ -  #277 = Utf8               exit
    │ -  #278 = Utf8               (I)V
    │ -  #279 = Utf8               TRUE
    │ -  #280 = Utf8               Ljava/lang/Boolean;
    │ -  #281 = Utf8               getOptionValue
    │ -  #282 = Utf8               (Ljargs/gnu/CmdLineParser$Option;)Ljava/lang/Object;
    │ -  #283 = Utf8               equals
    │ -  #284 = Utf8               (Ljava/lang/Object;)Z
    │ -  #285 = Utf8               out
    │ -  #286 = Utf8               (Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ -  #287 = Utf8               valueOf
    │ -  #288 = Utf8               (I)Ljava/lang/Integer;
    │ -  #289 = Utf8               intValue
    │ -  #290 = Utf8               ()I
    │ -  #291 = Utf8               (D)Ljava/lang/Double;
    │ -  #292 = Utf8               doubleValue
    │ -  #293 = Utf8               ()D
    │ -  #294 = Utf8               getRemainingArgs
    │ -  #295 = Utf8               ()[Ljava/lang/String;
    │ -  #296 = Utf8               com/turn/ttorrent/client/SharedTorrent
    │ -  #297 = Utf8               fromFile
    │ -  #298 = Utf8               (Ljava/io/File;Ljava/io/File;)Lcom/turn/ttorrent/client/SharedTorrent;
    │ -  #299 = Utf8               (Ljava/net/InetAddress;Lcom/turn/ttorrent/client/SharedTorrent;)V
    │ -  #300 = Utf8               setMaxDownloadRate
    │ -  #301 = Utf8               (D)V
    │ -  #302 = Utf8               setMaxUploadRate
    │ -  #303 = Utf8               java/lang/Runtime
    │ -  #304 = Utf8               getRuntime
    │ -  #305 = Utf8               ()Ljava/lang/Runtime;
    │ -  #306 = Utf8               (Lcom/turn/ttorrent/client/Client;Ljava/util/Timer;)V
    │ -  #307 = Utf8               (Ljava/lang/Runnable;)V
    │ -  #308 = Utf8               addShutdownHook
    │ -  #309 = Utf8               (Ljava/lang/Thread;)V
    │ -  #310 = Utf8               share
    │ -  #311 = Utf8               com/turn/ttorrent/client/Client$ClientState
    │ -  #312 = Utf8               ClientState
    │ -  #313 = Utf8               ERROR
    │ -  #314 = Utf8               Lcom/turn/ttorrent/client/Client$ClientState;
    │ -  #315 = Utf8               getState
    │ -  #316 = Utf8               ()Lcom/turn/ttorrent/client/Client$ClientState;
    │ -  #317 = Utf8               org/slf4j/Logger
    │ -  #318 = Utf8               error
    │ -  #319 = Utf8               (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    │ -  #320 = Utf8               org/slf4j/LoggerFactory
    │ -  #321 = Utf8               getLogger
    │ -  #322 = Utf8               (Ljava/lang/Class;)Lorg/slf4j/Logger;
    │ +    #1 = Methodref          #2.#3         // java/lang/Object."<init>":()V
    │ +    #2 = Class              #4            // java/lang/Object
    │ +    #3 = NameAndType        #5:#6         // "<init>":()V
    │ +    #4 = Utf8               java/lang/Object
    │ +    #5 = Utf8               <init>
    │ +    #6 = Utf8               ()V
    │ +    #7 = Methodref          #8.#9         // java/net/NetworkInterface.getByName:(Ljava/lang/String;)Ljava/net/NetworkInterface;
    │ +    #8 = Class              #10           // java/net/NetworkInterface
    │ +    #9 = NameAndType        #11:#12       // getByName:(Ljava/lang/String;)Ljava/net/NetworkInterface;
    │ +   #10 = Utf8               java/net/NetworkInterface
    │ +   #11 = Utf8               getByName
    │ +   #12 = Utf8               (Ljava/lang/String;)Ljava/net/NetworkInterface;
    │ +   #13 = Methodref          #8.#14        // java/net/NetworkInterface.getInetAddresses:()Ljava/util/Enumeration;
    │ +   #14 = NameAndType        #15:#16       // getInetAddresses:()Ljava/util/Enumeration;
    │ +   #15 = Utf8               getInetAddresses
    │ +   #16 = Utf8               ()Ljava/util/Enumeration;
    │ +   #17 = InterfaceMethodref #18.#19       // java/util/Enumeration.hasMoreElements:()Z
    │ +   #18 = Class              #20           // java/util/Enumeration
    │ +   #19 = NameAndType        #21:#22       // hasMoreElements:()Z
    │ +   #20 = Utf8               java/util/Enumeration
    │ +   #21 = Utf8               hasMoreElements
    │ +   #22 = Utf8               ()Z
    │ +   #23 = InterfaceMethodref #18.#24       // java/util/Enumeration.nextElement:()Ljava/lang/Object;
    │ +   #24 = NameAndType        #25:#26       // nextElement:()Ljava/lang/Object;
    │ +   #25 = Utf8               nextElement
    │ +   #26 = Utf8               ()Ljava/lang/Object;
    │ +   #27 = Class              #28           // java/net/InetAddress
    │ +   #28 = Utf8               java/net/InetAddress
    │ +   #29 = Class              #30           // java/net/Inet4Address
    │ +   #30 = Utf8               java/net/Inet4Address
    │ +   #31 = Methodref          #27.#32       // java/net/InetAddress.getLocalHost:()Ljava/net/InetAddress;
    │ +   #32 = NameAndType        #33:#34       // getLocalHost:()Ljava/net/InetAddress;
    │ +   #33 = Utf8               getLocalHost
    │ +   #34 = Utf8               ()Ljava/net/InetAddress;
    │ +   #35 = Class              #36           // java/nio/channels/UnsupportedAddressTypeException
    │ +   #36 = Utf8               java/nio/channels/UnsupportedAddressTypeException
    │ +   #37 = Methodref          #35.#3        // java/nio/channels/UnsupportedAddressTypeException."<init>":()V
    │ +   #38 = String             #39           // usage: Client [options] <torrent>
    │ +   #39 = Utf8               usage: Client [options] <torrent>
    │ +   #40 = Methodref          #41.#42       // java/io/PrintStream.println:(Ljava/lang/String;)V
    │ +   #41 = Class              #43           // java/io/PrintStream
    │ +   #42 = NameAndType        #44:#45       // println:(Ljava/lang/String;)V
    │ +   #43 = Utf8               java/io/PrintStream
    │ +   #44 = Utf8               println
    │ +   #45 = Utf8               (Ljava/lang/String;)V
    │ +   #46 = Methodref          #41.#47       // java/io/PrintStream.println:()V
    │ +   #47 = NameAndType        #44:#6        // println:()V
    │ +   #48 = String             #49           // Available options:
    │ +   #49 = Utf8               Available options:
    │ +   #50 = String             #51           //   -h,--help                  Show this help and exit.
    │ +   #51 = Utf8                 -h,--help                  Show this help and exit.
    │ +   #52 = String             #53           //   -o,--output DIR            Read/write data to directory DIR.
    │ +   #53 = Utf8                 -o,--output DIR            Read/write data to directory DIR.
    │ +   #54 = String             #55           //   -i,--iface IFACE           Bind to interface IFACE.
    │ +   #55 = Utf8                 -i,--iface IFACE           Bind to interface IFACE.
    │ +   #56 = String             #57           //   -s,--seed SECONDS          Time to seed after downloading (default: infinitely).
    │ +   #57 = Utf8                 -s,--seed SECONDS          Time to seed after downloading (default: infinitely).
    │ +   #58 = String             #59           //   -d,--max-download KB/SEC   Max download rate (default: unlimited).
    │ +   #59 = Utf8                 -d,--max-download KB/SEC   Max download rate (default: unlimited).
    │ +   #60 = String             #61           //   -u,--max-upload KB/SEC     Max upload rate (default: unlimited).
    │ +   #61 = Utf8                 -u,--max-upload KB/SEC     Max upload rate (default: unlimited).
    │ +   #62 = Class              #63           // org/apache/log4j/ConsoleAppender
    │ +   #63 = Utf8               org/apache/log4j/ConsoleAppender
    │ +   #64 = Class              #65           // org/apache/log4j/PatternLayout
    │ +   #65 = Utf8               org/apache/log4j/PatternLayout
    │ +   #66 = String             #67           // %d [%-25t] %-5p: %m%n
    │ +   #67 = Utf8               %d [%-25t] %-5p: %m%n
    │ +   #68 = Methodref          #64.#69       // org/apache/log4j/PatternLayout."<init>":(Ljava/lang/String;)V
    │ +   #69 = NameAndType        #5:#45        // "<init>":(Ljava/lang/String;)V
    │ +   #70 = Methodref          #62.#71       // org/apache/log4j/ConsoleAppender."<init>":(Lorg/apache/log4j/Layout;)V
    │ +   #71 = NameAndType        #5:#72        // "<init>":(Lorg/apache/log4j/Layout;)V
    │ +   #72 = Utf8               (Lorg/apache/log4j/Layout;)V
    │ +   #73 = Methodref          #74.#75       // org/apache/log4j/BasicConfigurator.configure:(Lorg/apache/log4j/Appender;)V
    │ +   #74 = Class              #76           // org/apache/log4j/BasicConfigurator
    │ +   #75 = NameAndType        #77:#78       // configure:(Lorg/apache/log4j/Appender;)V
    │ +   #76 = Utf8               org/apache/log4j/BasicConfigurator
    │ +   #77 = Utf8               configure
    │ +   #78 = Utf8               (Lorg/apache/log4j/Appender;)V
    │ +   #79 = Class              #80           // jargs/gnu/CmdLineParser
    │ +   #80 = Utf8               jargs/gnu/CmdLineParser
    │ +   #81 = Methodref          #79.#3        // jargs/gnu/CmdLineParser."<init>":()V
    │ +   #82 = String             #83           // help
    │ +   #83 = Utf8               help
    │ +   #84 = Methodref          #79.#85       // jargs/gnu/CmdLineParser.addBooleanOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +   #85 = NameAndType        #86:#87       // addBooleanOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +   #86 = Utf8               addBooleanOption
    │ +   #87 = Utf8               (CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +   #88 = String             #89           // output
    │ +   #89 = Utf8               output
    │ +   #90 = Methodref          #79.#91       // jargs/gnu/CmdLineParser.addStringOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +   #91 = NameAndType        #92:#87       // addStringOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +   #92 = Utf8               addStringOption
    │ +   #93 = String             #94           // iface
    │ +   #94 = Utf8               iface
    │ +   #95 = String             #96           // seed
    │ +   #96 = Utf8               seed
    │ +   #97 = Methodref          #79.#98       // jargs/gnu/CmdLineParser.addIntegerOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +   #98 = NameAndType        #99:#87       // addIntegerOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +   #99 = Utf8               addIntegerOption
    │ +  #100 = String             #101          // max-upload
    │ +  #101 = Utf8               max-upload
    │ +  #102 = Methodref          #79.#103      // jargs/gnu/CmdLineParser.addDoubleOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +  #103 = NameAndType        #104:#87      // addDoubleOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +  #104 = Utf8               addDoubleOption
    │ +  #105 = String             #106          // max-download
    │ +  #106 = Utf8               max-download
    │ +  #107 = Methodref          #79.#108      // jargs/gnu/CmdLineParser.parse:([Ljava/lang/String;)V
    │ +  #108 = NameAndType        #109:#110     // parse:([Ljava/lang/String;)V
    │ +  #109 = Utf8               parse
    │ +  #110 = Utf8               ([Ljava/lang/String;)V
    │ +  #111 = Class              #112          // jargs/gnu/CmdLineParser$OptionException
    │ +  #112 = Utf8               jargs/gnu/CmdLineParser$OptionException
    │ +  #113 = Fieldref           #114.#115     // java/lang/System.err:Ljava/io/PrintStream;
    │ +  #114 = Class              #116          // java/lang/System
    │ +  #115 = NameAndType        #117:#118     // err:Ljava/io/PrintStream;
    │ +  #116 = Utf8               java/lang/System
    │ +  #117 = Utf8               err
    │ +  #118 = Utf8               Ljava/io/PrintStream;
    │ +  #119 = Methodref          #111.#120     // jargs/gnu/CmdLineParser$OptionException.getMessage:()Ljava/lang/String;
    │ +  #120 = NameAndType        #121:#122     // getMessage:()Ljava/lang/String;
    │ +  #121 = Utf8               getMessage
    │ +  #122 = Utf8               ()Ljava/lang/String;
    │ +  #123 = Methodref          #124.#125     // com/turn/ttorrent/cli/ClientMain.usage:(Ljava/io/PrintStream;)V
    │ +  #124 = Class              #126          // com/turn/ttorrent/cli/ClientMain
    │ +  #125 = NameAndType        #127:#128     // usage:(Ljava/io/PrintStream;)V
    │ +  #126 = Utf8               com/turn/ttorrent/cli/ClientMain
    │ +  #127 = Utf8               usage
    │ +  #128 = Utf8               (Ljava/io/PrintStream;)V
    │ +  #129 = Methodref          #114.#130     // java/lang/System.exit:(I)V
    │ +  #130 = NameAndType        #131:#132     // exit:(I)V
    │ +  #131 = Utf8               exit
    │ +  #132 = Utf8               (I)V
    │ +  #133 = Fieldref           #134.#135     // java/lang/Boolean.TRUE:Ljava/lang/Boolean;
    │ +  #134 = Class              #136          // java/lang/Boolean
    │ +  #135 = NameAndType        #137:#138     // TRUE:Ljava/lang/Boolean;
    │ +  #136 = Utf8               java/lang/Boolean
    │ +  #137 = Utf8               TRUE
    │ +  #138 = Utf8               Ljava/lang/Boolean;
    │ +  #139 = Methodref          #79.#140      // jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;)Ljava/lang/Object;
    │ +  #140 = NameAndType        #141:#142     // getOptionValue:(Ljargs/gnu/CmdLineParser$Option;)Ljava/lang/Object;
    │ +  #141 = Utf8               getOptionValue
    │ +  #142 = Utf8               (Ljargs/gnu/CmdLineParser$Option;)Ljava/lang/Object;
    │ +  #143 = Methodref          #134.#144     // java/lang/Boolean.equals:(Ljava/lang/Object;)Z
    │ +  #144 = NameAndType        #145:#146     // equals:(Ljava/lang/Object;)Z
    │ +  #145 = Utf8               equals
    │ +  #146 = Utf8               (Ljava/lang/Object;)Z
    │ +  #147 = Fieldref           #114.#148     // java/lang/System.out:Ljava/io/PrintStream;
    │ +  #148 = NameAndType        #149:#118     // out:Ljava/io/PrintStream;
    │ +  #149 = Utf8               out
    │ +  #150 = String             #151          // /tmp
    │ +  #151 = Utf8               /tmp
    │ +  #152 = Methodref          #79.#153      // jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ +  #153 = NameAndType        #141:#154     // getOptionValue:(Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ +  #154 = Utf8               (Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ +  #155 = Class              #156          // java/lang/String
    │ +  #156 = Utf8               java/lang/String
    │ +  #157 = Methodref          #158.#159     // java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
    │ +  #158 = Class              #160          // java/lang/Integer
    │ +  #159 = NameAndType        #161:#162     // valueOf:(I)Ljava/lang/Integer;
    │ +  #160 = Utf8               java/lang/Integer
    │ +  #161 = Utf8               valueOf
    │ +  #162 = Utf8               (I)Ljava/lang/Integer;
    │ +  #163 = Methodref          #158.#164     // java/lang/Integer.intValue:()I
    │ +  #164 = NameAndType        #165:#166     // intValue:()I
    │ +  #165 = Utf8               intValue
    │ +  #166 = Utf8               ()I
    │ +  #167 = Methodref          #168.#169     // java/lang/Double.valueOf:(D)Ljava/lang/Double;
    │ +  #168 = Class              #170          // java/lang/Double
    │ +  #169 = NameAndType        #161:#171     // valueOf:(D)Ljava/lang/Double;
    │ +  #170 = Utf8               java/lang/Double
    │ +  #171 = Utf8               (D)Ljava/lang/Double;
    │ +  #172 = Methodref          #168.#173     // java/lang/Double.doubleValue:()D
    │ +  #173 = NameAndType        #174:#175     // doubleValue:()D
    │ +  #174 = Utf8               doubleValue
    │ +  #175 = Utf8               ()D
    │ +  #176 = Methodref          #79.#177      // jargs/gnu/CmdLineParser.getRemainingArgs:()[Ljava/lang/String;
    │ +  #177 = NameAndType        #178:#179     // getRemainingArgs:()[Ljava/lang/String;
    │ +  #178 = Utf8               getRemainingArgs
    │ +  #179 = Utf8               ()[Ljava/lang/String;
    │ +  #180 = Class              #181          // com/turn/ttorrent/client/Client
    │ +  #181 = Utf8               com/turn/ttorrent/client/Client
    │ +  #182 = Methodref          #124.#183     // com/turn/ttorrent/cli/ClientMain.getIPv4Address:(Ljava/lang/String;)Ljava/net/Inet4Address;
    │ +  #183 = NameAndType        #184:#185     // getIPv4Address:(Ljava/lang/String;)Ljava/net/Inet4Address;
    │ +  #184 = Utf8               getIPv4Address
    │ +  #185 = Utf8               (Ljava/lang/String;)Ljava/net/Inet4Address;
    │ +  #186 = Class              #187          // java/io/File
    │ +  #187 = Utf8               java/io/File
    │ +  #188 = Methodref          #186.#69      // java/io/File."<init>":(Ljava/lang/String;)V
    │ +  #189 = Methodref          #190.#191     // com/turn/ttorrent/client/SharedTorrent.fromFile:(Ljava/io/File;Ljava/io/File;)Lcom/turn/ttorrent/client/SharedTorrent;
    │ +  #190 = Class              #192          // com/turn/ttorrent/client/SharedTorrent
    │ +  #191 = NameAndType        #193:#194     // fromFile:(Ljava/io/File;Ljava/io/File;)Lcom/turn/ttorrent/client/SharedTorrent;
    │ +  #192 = Utf8               com/turn/ttorrent/client/SharedTorrent
    │ +  #193 = Utf8               fromFile
    │ +  #194 = Utf8               (Ljava/io/File;Ljava/io/File;)Lcom/turn/ttorrent/client/SharedTorrent;
    │ +  #195 = Methodref          #180.#196     // com/turn/ttorrent/client/Client."<init>":(Ljava/net/InetAddress;Lcom/turn/ttorrent/client/SharedTorrent;)V
    │ +  #196 = NameAndType        #5:#197       // "<init>":(Ljava/net/InetAddress;Lcom/turn/ttorrent/client/SharedTorrent;)V
    │ +  #197 = Utf8               (Ljava/net/InetAddress;Lcom/turn/ttorrent/client/SharedTorrent;)V
    │ +  #198 = Methodref          #180.#199     // com/turn/ttorrent/client/Client.setMaxDownloadRate:(D)V
    │ +  #199 = NameAndType        #200:#201     // setMaxDownloadRate:(D)V
    │ +  #200 = Utf8               setMaxDownloadRate
    │ +  #201 = Utf8               (D)V
    │ +  #202 = Methodref          #180.#203     // com/turn/ttorrent/client/Client.setMaxUploadRate:(D)V
    │ +  #203 = NameAndType        #204:#201     // setMaxUploadRate:(D)V
    │ +  #204 = Utf8               setMaxUploadRate
    │ +  #205 = Methodref          #206.#207     // java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
    │ +  #206 = Class              #208          // java/lang/Runtime
    │ +  #207 = NameAndType        #209:#210     // getRuntime:()Ljava/lang/Runtime;
    │ +  #208 = Utf8               java/lang/Runtime
    │ +  #209 = Utf8               getRuntime
    │ +  #210 = Utf8               ()Ljava/lang/Runtime;
    │ +  #211 = Class              #212          // java/lang/Thread
    │ +  #212 = Utf8               java/lang/Thread
    │ +  #213 = Class              #214          // com/turn/ttorrent/client/Client$ClientShutdown
    │ +  #214 = Utf8               com/turn/ttorrent/client/Client$ClientShutdown
    │ +  #215 = Methodref          #213.#216     // com/turn/ttorrent/client/Client$ClientShutdown."<init>":(Lcom/turn/ttorrent/client/Client;Ljava/util/Timer;)V
    │ +  #216 = NameAndType        #5:#217       // "<init>":(Lcom/turn/ttorrent/client/Client;Ljava/util/Timer;)V
    │ +  #217 = Utf8               (Lcom/turn/ttorrent/client/Client;Ljava/util/Timer;)V
    │ +  #218 = Methodref          #211.#219     // java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
    │ +  #219 = NameAndType        #5:#220       // "<init>":(Ljava/lang/Runnable;)V
    │ +  #220 = Utf8               (Ljava/lang/Runnable;)V
    │ +  #221 = Methodref          #206.#222     // java/lang/Runtime.addShutdownHook:(Ljava/lang/Thread;)V
    │ +  #222 = NameAndType        #223:#224     // addShutdownHook:(Ljava/lang/Thread;)V
    │ +  #223 = Utf8               addShutdownHook
    │ +  #224 = Utf8               (Ljava/lang/Thread;)V
    │ +  #225 = Methodref          #180.#226     // com/turn/ttorrent/client/Client.share:(I)V
    │ +  #226 = NameAndType        #227:#132     // share:(I)V
    │ +  #227 = Utf8               share
    │ +  #228 = Fieldref           #229.#230     // com/turn/ttorrent/client/Client$ClientState.ERROR:Lcom/turn/ttorrent/client/Client$ClientState;
    │ +  #229 = Class              #231          // com/turn/ttorrent/client/Client$ClientState
    │ +  #230 = NameAndType        #232:#233     // ERROR:Lcom/turn/ttorrent/client/Client$ClientState;
    │ +  #231 = Utf8               com/turn/ttorrent/client/Client$ClientState
    │ +  #232 = Utf8               ERROR
    │ +  #233 = Utf8               Lcom/turn/ttorrent/client/Client$ClientState;
    │ +  #234 = Methodref          #180.#235     // com/turn/ttorrent/client/Client.getState:()Lcom/turn/ttorrent/client/Client$ClientState;
    │ +  #235 = NameAndType        #236:#237     // getState:()Lcom/turn/ttorrent/client/Client$ClientState;
    │ +  #236 = Utf8               getState
    │ +  #237 = Utf8               ()Lcom/turn/ttorrent/client/Client$ClientState;
    │ +  #238 = Methodref          #229.#144     // com/turn/ttorrent/client/Client$ClientState.equals:(Ljava/lang/Object;)Z
    │ +  #239 = Class              #240          // java/lang/Exception
    │ +  #240 = Utf8               java/lang/Exception
    │ +  #241 = Fieldref           #124.#242     // com/turn/ttorrent/cli/ClientMain.logger:Lorg/slf4j/Logger;
    │ +  #242 = NameAndType        #243:#244     // logger:Lorg/slf4j/Logger;
    │ +  #243 = Utf8               logger
    │ +  #244 = Utf8               Lorg/slf4j/Logger;
    │ +  #245 = String             #246          // Fatal error: {}
    │ +  #246 = Utf8               Fatal error: {}
    │ +  #247 = Methodref          #239.#120     // java/lang/Exception.getMessage:()Ljava/lang/String;
    │ +  #248 = InterfaceMethodref #249.#250     // org/slf4j/Logger.error:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    │ +  #249 = Class              #251          // org/slf4j/Logger
    │ +  #250 = NameAndType        #252:#253     // error:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    │ +  #251 = Utf8               org/slf4j/Logger
    │ +  #252 = Utf8               error
    │ +  #253 = Utf8               (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    │ +  #254 = Methodref          #255.#256     // org/slf4j/LoggerFactory.getLogger:(Ljava/lang/Class;)Lorg/slf4j/Logger;
    │ +  #255 = Class              #257          // org/slf4j/LoggerFactory
    │ +  #256 = NameAndType        #258:#259     // getLogger:(Ljava/lang/Class;)Lorg/slf4j/Logger;
    │ +  #257 = Utf8               org/slf4j/LoggerFactory
    │ +  #258 = Utf8               getLogger
    │ +  #259 = Utf8               (Ljava/lang/Class;)Lorg/slf4j/Logger;
    │ +  #260 = Utf8               DEFAULT_OUTPUT_DIRECTORY
    │ +  #261 = Utf8               Ljava/lang/String;
    │ +  #262 = Utf8               ConstantValue
    │ +  #263 = Utf8               Code
    │ +  #264 = Utf8               LineNumberTable
    │ +  #265 = Utf8               LocalVariableTable
    │ +  #266 = Utf8               this
    │ +  #267 = Utf8               Lcom/turn/ttorrent/cli/ClientMain;
    │ +  #268 = Utf8               addr
    │ +  #269 = Utf8               Ljava/net/InetAddress;
    │ +  #270 = Utf8               addresses
    │ +  #271 = Utf8               Ljava/util/Enumeration;
    │ +  #272 = Utf8               localhost
    │ +  #273 = Utf8               LocalVariableTypeTable
    │ +  #274 = Utf8               Ljava/util/Enumeration<Ljava/net/InetAddress;>;
    │ +  #275 = Utf8               StackMapTable
    │ +  #276 = Utf8               Exceptions
    │ +  #277 = Class              #278          // java/net/SocketException
    │ +  #278 = Utf8               java/net/SocketException
    │ +  #279 = Class              #280          // java/net/UnknownHostException
    │ +  #280 = Utf8               java/net/UnknownHostException
    │ +  #281 = Utf8               s
    │ +  #282 = Utf8               main
    │ +  #283 = Utf8               oe
    │ +  #284 = Utf8               Ljargs/gnu/CmdLineParser$OptionException;
    │ +  #285 = Utf8               c
    │ +  #286 = Utf8               Lcom/turn/ttorrent/client/Client;
    │ +  #287 = Utf8               e
    │ +  #288 = Utf8               Ljava/lang/Exception;
    │ +  #289 = Utf8               args
    │ +  #290 = Utf8               [Ljava/lang/String;
    │ +  #291 = Utf8               parser
    │ +  #292 = Utf8               Ljargs/gnu/CmdLineParser;
    │ +  #293 = Utf8               Ljargs/gnu/CmdLineParser$Option;
    │ +  #294 = Utf8               seedTime
    │ +  #295 = Utf8               maxUpload
    │ +  #296 = Utf8               maxDownload
    │ +  #297 = Utf8               outputValue
    │ +  #298 = Utf8               ifaceValue
    │ +  #299 = Utf8               seedTimeValue
    │ +  #300 = Utf8               I
    │ +  #301 = Utf8               maxDownloadRate
    │ +  #302 = Utf8               D
    │ +  #303 = Utf8               maxUploadRate
    │ +  #304 = Utf8               otherArgs
    │ +  #305 = Class              #290          // "[Ljava/lang/String;"
    │ +  #306 = Class              #307          // jargs/gnu/CmdLineParser$Option
    │ +  #307 = Utf8               jargs/gnu/CmdLineParser$Option
    │ +  #308 = Utf8               <clinit>
    │ +  #309 = Utf8               SourceFile
    │ +  #310 = Utf8               ClientMain.java
    │ +  #311 = Utf8               InnerClasses
    │ +  #312 = Utf8               Option
    │ +  #313 = Utf8               OptionException
    │ +  #314 = Utf8               ClientShutdown
    │ +  #315 = Utf8               ClientState
    │  {
    │    private static final org.slf4j.Logger logger;
    │      descriptor: Lorg/slf4j/Logger;
    │      flags: (0x001a) ACC_PRIVATE, ACC_STATIC, ACC_FINAL
    │  
    │    private static final java.lang.String DEFAULT_OUTPUT_DIRECTORY = "/tmp";
    │      descriptor: Ljava/lang/String;
    │ @@ -358,42 +351,42 @@
    │      descriptor: (Ljava/lang/String;)Ljava/net/Inet4Address;
    │      flags: (0x000a) ACC_PRIVATE, ACC_STATIC
    │      Code:
    │        stack=2, locals=3, args_size=1
    │           0: aload_0
    │           1: ifnull        46
    │           4: aload_0
    │ -         5: invokestatic  #2                  // Method java/net/NetworkInterface.getByName:(Ljava/lang/String;)Ljava/net/NetworkInterface;
    │ -         8: invokevirtual #3                  // Method java/net/NetworkInterface.getInetAddresses:()Ljava/util/Enumeration;
    │ +         5: invokestatic  #7                  // Method java/net/NetworkInterface.getByName:(Ljava/lang/String;)Ljava/net/NetworkInterface;
    │ +         8: invokevirtual #13                 // Method java/net/NetworkInterface.getInetAddresses:()Ljava/util/Enumeration;
    │          11: astore_1
    │          12: aload_1
    │ -        13: invokeinterface #4,  1            // InterfaceMethod java/util/Enumeration.hasMoreElements:()Z
    │ +        13: invokeinterface #17,  1           // InterfaceMethod java/util/Enumeration.hasMoreElements:()Z
    │          18: ifeq          46
    │          21: aload_1
    │ -        22: invokeinterface #5,  1            // InterfaceMethod java/util/Enumeration.nextElement:()Ljava/lang/Object;
    │ -        27: checkcast     #6                  // class java/net/InetAddress
    │ +        22: invokeinterface #23,  1           // InterfaceMethod java/util/Enumeration.nextElement:()Ljava/lang/Object;
    │ +        27: checkcast     #27                 // class java/net/InetAddress
    │          30: astore_2
    │          31: aload_2
    │ -        32: instanceof    #7                  // class java/net/Inet4Address
    │ +        32: instanceof    #29                 // class java/net/Inet4Address
    │          35: ifeq          43
    │          38: aload_2
    │ -        39: checkcast     #7                  // class java/net/Inet4Address
    │ +        39: checkcast     #29                 // class java/net/Inet4Address
    │          42: areturn
    │          43: goto          12
    │ -        46: invokestatic  #8                  // Method java/net/InetAddress.getLocalHost:()Ljava/net/InetAddress;
    │ +        46: invokestatic  #31                 // Method java/net/InetAddress.getLocalHost:()Ljava/net/InetAddress;
    │          49: astore_1
    │          50: aload_1
    │ -        51: instanceof    #7                  // class java/net/Inet4Address
    │ +        51: instanceof    #29                 // class java/net/Inet4Address
    │          54: ifeq          62
    │          57: aload_1
    │ -        58: checkcast     #7                  // class java/net/Inet4Address
    │ +        58: checkcast     #29                 // class java/net/Inet4Address
    │          61: areturn
    │ -        62: new           #9                  // class java/nio/channels/UnsupportedAddressTypeException
    │ +        62: new           #35                 // class java/nio/channels/UnsupportedAddressTypeException
    │          65: dup
    │ -        66: invokespecial #10                 // Method java/nio/channels/UnsupportedAddressTypeException."<init>":()V
    │ +        66: invokespecial #37                 // Method java/nio/channels/UnsupportedAddressTypeException."<init>":()V
    │          69: athrow
    │        LineNumberTable:
    │          line 74: 0
    │          line 75: 4
    │          line 76: 5
    │          line 77: 12
    │          line 78: 21
    │ @@ -428,41 +421,41 @@
    │  
    │    private static void usage(java.io.PrintStream);
    │      descriptor: (Ljava/io/PrintStream;)V
    │      flags: (0x000a) ACC_PRIVATE, ACC_STATIC
    │      Code:
    │        stack=2, locals=1, args_size=1
    │           0: aload_0
    │ -         1: ldc           #11                 // String usage: Client [options] <torrent>
    │ -         3: invokevirtual #12                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │ +         1: ldc           #38                 // String usage: Client [options] <torrent>
    │ +         3: invokevirtual #40                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │           6: aload_0
    │ -         7: invokevirtual #13                 // Method java/io/PrintStream.println:()V
    │ +         7: invokevirtual #46                 // Method java/io/PrintStream.println:()V
    │          10: aload_0
    │ -        11: ldc           #14                 // String Available options:
    │ -        13: invokevirtual #12                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │ +        11: ldc           #48                 // String Available options:
    │ +        13: invokevirtual #40                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │          16: aload_0
    │ -        17: ldc           #15                 // String   -h,--help                  Show this help and exit.
    │ -        19: invokevirtual #12                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │ +        17: ldc           #50                 // String   -h,--help                  Show this help and exit.
    │ +        19: invokevirtual #40                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │          22: aload_0
    │ -        23: ldc           #16                 // String   -o,--output DIR            Read/write data to directory DIR.
    │ -        25: invokevirtual #12                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │ +        23: ldc           #52                 // String   -o,--output DIR            Read/write data to directory DIR.
    │ +        25: invokevirtual #40                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │          28: aload_0
    │ -        29: ldc           #17                 // String   -i,--iface IFACE           Bind to interface IFACE.
    │ -        31: invokevirtual #12                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │ +        29: ldc           #54                 // String   -i,--iface IFACE           Bind to interface IFACE.
    │ +        31: invokevirtual #40                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │          34: aload_0
    │ -        35: ldc           #18                 // String   -s,--seed SECONDS          Time to seed after downloading (default: infinitely).
    │ -        37: invokevirtual #12                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │ +        35: ldc           #56                 // String   -s,--seed SECONDS          Time to seed after downloading (default: infinitely).
    │ +        37: invokevirtual #40                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │          40: aload_0
    │ -        41: ldc           #19                 // String   -d,--max-download KB/SEC   Max download rate (default: unlimited).
    │ -        43: invokevirtual #12                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │ +        41: ldc           #58                 // String   -d,--max-download KB/SEC   Max download rate (default: unlimited).
    │ +        43: invokevirtual #40                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │          46: aload_0
    │ -        47: ldc           #20                 // String   -u,--max-upload KB/SEC     Max upload rate (default: unlimited).
    │ -        49: invokevirtual #12                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │ +        47: ldc           #60                 // String   -u,--max-upload KB/SEC     Max upload rate (default: unlimited).
    │ +        49: invokevirtual #40                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │          52: aload_0
    │ -        53: invokevirtual #13                 // Method java/io/PrintStream.println:()V
    │ +        53: invokevirtual #46                 // Method java/io/PrintStream.println:()V
    │          56: return
    │        LineNumberTable:
    │          line 97: 0
    │          line 98: 6
    │          line 99: 10
    │          line 100: 16
    │          line 101: 22
    │ @@ -477,179 +470,179 @@
    │              0      57     0     s   Ljava/io/PrintStream;
    │  
    │    public static void main(java.lang.String[]);
    │      descriptor: ([Ljava/lang/String;)V
    │      flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    │      Code:
    │        stack=7, locals=17, args_size=1
    │ -         0: new           #21                 // class org/apache/log4j/ConsoleAppender
    │ +         0: new           #62                 // class org/apache/log4j/ConsoleAppender
    │           3: dup
    │ -         4: new           #22                 // class org/apache/log4j/PatternLayout
    │ +         4: new           #64                 // class org/apache/log4j/PatternLayout
    │           7: dup
    │ -         8: ldc           #23                 // String %d [%-25t] %-5p: %m%n
    │ -        10: invokespecial #24                 // Method org/apache/log4j/PatternLayout."<init>":(Ljava/lang/String;)V
    │ -        13: invokespecial #25                 // Method org/apache/log4j/ConsoleAppender."<init>":(Lorg/apache/log4j/Layout;)V
    │ -        16: invokestatic  #26                 // Method org/apache/log4j/BasicConfigurator.configure:(Lorg/apache/log4j/Appender;)V
    │ -        19: new           #27                 // class jargs/gnu/CmdLineParser
    │ +         8: ldc           #66                 // String %d [%-25t] %-5p: %m%n
    │ +        10: invokespecial #68                 // Method org/apache/log4j/PatternLayout."<init>":(Ljava/lang/String;)V
    │ +        13: invokespecial #70                 // Method org/apache/log4j/ConsoleAppender."<init>":(Lorg/apache/log4j/Layout;)V
    │ +        16: invokestatic  #73                 // Method org/apache/log4j/BasicConfigurator.configure:(Lorg/apache/log4j/Appender;)V
    │ +        19: new           #79                 // class jargs/gnu/CmdLineParser
    │          22: dup
    │ -        23: invokespecial #28                 // Method jargs/gnu/CmdLineParser."<init>":()V
    │ +        23: invokespecial #81                 // Method jargs/gnu/CmdLineParser."<init>":()V
    │          26: astore_1
    │          27: aload_1
    │          28: bipush        104
    │ -        30: ldc           #29                 // String help
    │ -        32: invokevirtual #30                 // Method jargs/gnu/CmdLineParser.addBooleanOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +        30: ldc           #82                 // String help
    │ +        32: invokevirtual #84                 // Method jargs/gnu/CmdLineParser.addBooleanOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │          35: astore_2
    │          36: aload_1
    │          37: bipush        111
    │ -        39: ldc           #31                 // String output
    │ -        41: invokevirtual #32                 // Method jargs/gnu/CmdLineParser.addStringOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +        39: ldc           #88                 // String output
    │ +        41: invokevirtual #90                 // Method jargs/gnu/CmdLineParser.addStringOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │          44: astore_3
    │          45: aload_1
    │          46: bipush        105
    │ -        48: ldc           #33                 // String iface
    │ -        50: invokevirtual #32                 // Method jargs/gnu/CmdLineParser.addStringOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +        48: ldc           #93                 // String iface
    │ +        50: invokevirtual #90                 // Method jargs/gnu/CmdLineParser.addStringOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │          53: astore        4
    │          55: aload_1
    │          56: bipush        115
    │ -        58: ldc           #34                 // String seed
    │ -        60: invokevirtual #35                 // Method jargs/gnu/CmdLineParser.addIntegerOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +        58: ldc           #95                 // String seed
    │ +        60: invokevirtual #97                 // Method jargs/gnu/CmdLineParser.addIntegerOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │          63: astore        5
    │          65: aload_1
    │          66: bipush        117
    │ -        68: ldc           #36                 // String max-upload
    │ -        70: invokevirtual #37                 // Method jargs/gnu/CmdLineParser.addDoubleOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +        68: ldc           #100                // String max-upload
    │ +        70: invokevirtual #102                // Method jargs/gnu/CmdLineParser.addDoubleOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │          73: astore        6
    │          75: aload_1
    │          76: bipush        100
    │ -        78: ldc           #38                 // String max-download
    │ -        80: invokevirtual #37                 // Method jargs/gnu/CmdLineParser.addDoubleOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │ +        78: ldc           #105                // String max-download
    │ +        80: invokevirtual #102                // Method jargs/gnu/CmdLineParser.addDoubleOption:(CLjava/lang/String;)Ljargs/gnu/CmdLineParser$Option;
    │          83: astore        7
    │          85: aload_1
    │          86: aload_0
    │ -        87: invokevirtual #39                 // Method jargs/gnu/CmdLineParser.parse:([Ljava/lang/String;)V
    │ +        87: invokevirtual #107                // Method jargs/gnu/CmdLineParser.parse:([Ljava/lang/String;)V
    │          90: goto          116
    │          93: astore        8
    │ -        95: getstatic     #41                 // Field java/lang/System.err:Ljava/io/PrintStream;
    │ +        95: getstatic     #113                // Field java/lang/System.err:Ljava/io/PrintStream;
    │          98: aload         8
    │ -       100: invokevirtual #42                 // Method jargs/gnu/CmdLineParser$OptionException.getMessage:()Ljava/lang/String;
    │ -       103: invokevirtual #12                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │ -       106: getstatic     #41                 // Field java/lang/System.err:Ljava/io/PrintStream;
    │ -       109: invokestatic  #43                 // Method usage:(Ljava/io/PrintStream;)V
    │ +       100: invokevirtual #119                // Method jargs/gnu/CmdLineParser$OptionException.getMessage:()Ljava/lang/String;
    │ +       103: invokevirtual #40                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
    │ +       106: getstatic     #113                // Field java/lang/System.err:Ljava/io/PrintStream;
    │ +       109: invokestatic  #123                // Method usage:(Ljava/io/PrintStream;)V
    │         112: iconst_1
    │ -       113: invokestatic  #44                 // Method java/lang/System.exit:(I)V
    │ -       116: getstatic     #45                 // Field java/lang/Boolean.TRUE:Ljava/lang/Boolean;
    │ +       113: invokestatic  #129                // Method java/lang/System.exit:(I)V
    │ +       116: getstatic     #133                // Field java/lang/Boolean.TRUE:Ljava/lang/Boolean;
    │         119: aload_1
    │         120: aload_2
    │ -       121: invokevirtual #46                 // Method jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;)Ljava/lang/Object;
    │ -       124: checkcast     #47                 // class java/lang/Boolean
    │ -       127: invokevirtual #48                 // Method java/lang/Boolean.equals:(Ljava/lang/Object;)Z
    │ +       121: invokevirtual #139                // Method jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;)Ljava/lang/Object;
    │ +       124: checkcast     #134                // class java/lang/Boolean
    │ +       127: invokevirtual #143                // Method java/lang/Boolean.equals:(Ljava/lang/Object;)Z
    │         130: ifeq          143
    │ -       133: getstatic     #49                 // Field java/lang/System.out:Ljava/io/PrintStream;
    │ -       136: invokestatic  #43                 // Method usage:(Ljava/io/PrintStream;)V
    │ +       133: getstatic     #147                // Field java/lang/System.out:Ljava/io/PrintStream;
    │ +       136: invokestatic  #123                // Method usage:(Ljava/io/PrintStream;)V
    │         139: iconst_0
    │ -       140: invokestatic  #44                 // Method java/lang/System.exit:(I)V
    │ +       140: invokestatic  #129                // Method java/lang/System.exit:(I)V
    │         143: aload_1
    │         144: aload_3
    │ -       145: ldc           #51                 // String /tmp
    │ -       147: invokevirtual #52                 // Method jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ -       150: checkcast     #53                 // class java/lang/String
    │ +       145: ldc           #150                // String /tmp
    │ +       147: invokevirtual #152                // Method jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ +       150: checkcast     #155                // class java/lang/String
    │         153: astore        8
    │         155: aload_1
    │         156: aload         4
    │ -       158: invokevirtual #46                 // Method jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;)Ljava/lang/Object;
    │ -       161: checkcast     #53                 // class java/lang/String
    │ +       158: invokevirtual #139                // Method jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;)Ljava/lang/Object;
    │ +       161: checkcast     #155                // class java/lang/String
    │         164: astore        9
    │         166: aload_1
    │         167: aload         5
    │         169: iconst_m1
    │ -       170: invokestatic  #54                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
    │ -       173: invokevirtual #52                 // Method jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ -       176: checkcast     #55                 // class java/lang/Integer
    │ -       179: invokevirtual #56                 // Method java/lang/Integer.intValue:()I
    │ +       170: invokestatic  #157                // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
    │ +       173: invokevirtual #152                // Method jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ +       176: checkcast     #158                // class java/lang/Integer
    │ +       179: invokevirtual #163                // Method java/lang/Integer.intValue:()I
    │         182: istore        10
    │         184: aload_1
    │         185: aload         7
    │         187: dconst_0
    │ -       188: invokestatic  #57                 // Method java/lang/Double.valueOf:(D)Ljava/lang/Double;
    │ -       191: invokevirtual #52                 // Method jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ -       194: checkcast     #58                 // class java/lang/Double
    │ -       197: invokevirtual #59                 // Method java/lang/Double.doubleValue:()D
    │ +       188: invokestatic  #167                // Method java/lang/Double.valueOf:(D)Ljava/lang/Double;
    │ +       191: invokevirtual #152                // Method jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ +       194: checkcast     #168                // class java/lang/Double
    │ +       197: invokevirtual #172                // Method java/lang/Double.doubleValue:()D
    │         200: dstore        11
    │         202: aload_1
    │         203: aload         6
    │         205: dconst_0
    │ -       206: invokestatic  #57                 // Method java/lang/Double.valueOf:(D)Ljava/lang/Double;
    │ -       209: invokevirtual #52                 // Method jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ -       212: checkcast     #58                 // class java/lang/Double
    │ -       215: invokevirtual #59                 // Method java/lang/Double.doubleValue:()D
    │ +       206: invokestatic  #167                // Method java/lang/Double.valueOf:(D)Ljava/lang/Double;
    │ +       209: invokevirtual #152                // Method jargs/gnu/CmdLineParser.getOptionValue:(Ljargs/gnu/CmdLineParser$Option;Ljava/lang/Object;)Ljava/lang/Object;
    │ +       212: checkcast     #168                // class java/lang/Double
    │ +       215: invokevirtual #172                // Method java/lang/Double.doubleValue:()D
    │         218: dstore        13
    │         220: aload_1
    │ -       221: invokevirtual #60                 // Method jargs/gnu/CmdLineParser.getRemainingArgs:()[Ljava/lang/String;
    │ +       221: invokevirtual #176                // Method jargs/gnu/CmdLineParser.getRemainingArgs:()[Ljava/lang/String;
    │         224: astore        15
    │         226: aload         15
    │         228: arraylength
    │         229: iconst_1
    │         230: if_icmpeq     243
    │ -       233: getstatic     #41                 // Field java/lang/System.err:Ljava/io/PrintStream;
    │ -       236: invokestatic  #43                 // Method usage:(Ljava/io/PrintStream;)V
    │ +       233: getstatic     #113                // Field java/lang/System.err:Ljava/io/PrintStream;
    │ +       236: invokestatic  #123                // Method usage:(Ljava/io/PrintStream;)V
    │         239: iconst_1
    │ -       240: invokestatic  #44                 // Method java/lang/System.exit:(I)V
    │ -       243: new           #61                 // class com/turn/ttorrent/client/Client
    │ +       240: invokestatic  #129                // Method java/lang/System.exit:(I)V
    │ +       243: new           #180                // class com/turn/ttorrent/client/Client
    │         246: dup
    │         247: aload         9
    │ -       249: invokestatic  #62                 // Method getIPv4Address:(Ljava/lang/String;)Ljava/net/Inet4Address;
    │ -       252: new           #63                 // class java/io/File
    │ +       249: invokestatic  #182                // Method getIPv4Address:(Ljava/lang/String;)Ljava/net/Inet4Address;
    │ +       252: new           #186                // class java/io/File
    │         255: dup
    │         256: aload         15
    │         258: iconst_0
    │         259: aaload
    │ -       260: invokespecial #64                 // Method java/io/File."<init>":(Ljava/lang/String;)V
    │ -       263: new           #63                 // class java/io/File
    │ +       260: invokespecial #188                // Method java/io/File."<init>":(Ljava/lang/String;)V
    │ +       263: new           #186                // class java/io/File
    │         266: dup
    │         267: aload         8
    │ -       269: invokespecial #64                 // Method java/io/File."<init>":(Ljava/lang/String;)V
    │ -       272: invokestatic  #65                 // Method com/turn/ttorrent/client/SharedTorrent.fromFile:(Ljava/io/File;Ljava/io/File;)Lcom/turn/ttorrent/client/SharedTorrent;
    │ -       275: invokespecial #66                 // Method com/turn/ttorrent/client/Client."<init>":(Ljava/net/InetAddress;Lcom/turn/ttorrent/client/SharedTorrent;)V
    │ +       269: invokespecial #188                // Method java/io/File."<init>":(Ljava/lang/String;)V
    │ +       272: invokestatic  #189                // Method com/turn/ttorrent/client/SharedTorrent.fromFile:(Ljava/io/File;Ljava/io/File;)Lcom/turn/ttorrent/client/SharedTorrent;
    │ +       275: invokespecial #195                // Method com/turn/ttorrent/client/Client."<init>":(Ljava/net/InetAddress;Lcom/turn/ttorrent/client/SharedTorrent;)V
    │         278: astore        16
    │         280: aload         16
    │         282: dload         11
    │ -       284: invokevirtual #67                 // Method com/turn/ttorrent/client/Client.setMaxDownloadRate:(D)V
    │ +       284: invokevirtual #198                // Method com/turn/ttorrent/client/Client.setMaxDownloadRate:(D)V
    │         287: aload         16
    │         289: dload         13
    │ -       291: invokevirtual #68                 // Method com/turn/ttorrent/client/Client.setMaxUploadRate:(D)V
    │ -       294: invokestatic  #69                 // Method java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
    │ -       297: new           #70                 // class java/lang/Thread
    │ +       291: invokevirtual #202                // Method com/turn/ttorrent/client/Client.setMaxUploadRate:(D)V
    │ +       294: invokestatic  #205                // Method java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
    │ +       297: new           #211                // class java/lang/Thread
    │         300: dup
    │ -       301: new           #71                 // class com/turn/ttorrent/client/Client$ClientShutdown
    │ +       301: new           #213                // class com/turn/ttorrent/client/Client$ClientShutdown
    │         304: dup
    │         305: aload         16
    │         307: aconst_null
    │ -       308: invokespecial #72                 // Method com/turn/ttorrent/client/Client$ClientShutdown."<init>":(Lcom/turn/ttorrent/client/Client;Ljava/util/Timer;)V
    │ -       311: invokespecial #73                 // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
    │ -       314: invokevirtual #74                 // Method java/lang/Runtime.addShutdownHook:(Ljava/lang/Thread;)V
    │ +       308: invokespecial #215                // Method com/turn/ttorrent/client/Client$ClientShutdown."<init>":(Lcom/turn/ttorrent/client/Client;Ljava/util/Timer;)V
    │ +       311: invokespecial #218                // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
    │ +       314: invokevirtual #221                // Method java/lang/Runtime.addShutdownHook:(Ljava/lang/Thread;)V
    │         317: aload         16
    │         319: iload         10
    │ -       321: invokevirtual #75                 // Method com/turn/ttorrent/client/Client.share:(I)V
    │ -       324: getstatic     #76                 // Field com/turn/ttorrent/client/Client$ClientState.ERROR:Lcom/turn/ttorrent/client/Client$ClientState;
    │ +       321: invokevirtual #225                // Method com/turn/ttorrent/client/Client.share:(I)V
    │ +       324: getstatic     #228                // Field com/turn/ttorrent/client/Client$ClientState.ERROR:Lcom/turn/ttorrent/client/Client$ClientState;
    │         327: aload         16
    │ -       329: invokevirtual #77                 // Method com/turn/ttorrent/client/Client.getState:()Lcom/turn/ttorrent/client/Client$ClientState;
    │ -       332: invokevirtual #78                 // Method com/turn/ttorrent/client/Client$ClientState.equals:(Ljava/lang/Object;)Z
    │ +       329: invokevirtual #234                // Method com/turn/ttorrent/client/Client.getState:()Lcom/turn/ttorrent/client/Client$ClientState;
    │ +       332: invokevirtual #238                // Method com/turn/ttorrent/client/Client$ClientState.equals:(Ljava/lang/Object;)Z
    │         335: ifeq          342
    │         338: iconst_1
    │ -       339: invokestatic  #44                 // Method java/lang/System.exit:(I)V
    │ +       339: invokestatic  #129                // Method java/lang/System.exit:(I)V
    │         342: goto          368
    │         345: astore        16
    │ -       347: getstatic     #80                 // Field logger:Lorg/slf4j/Logger;
    │ -       350: ldc           #81                 // String Fatal error: {}
    │ +       347: getstatic     #241                // Field logger:Lorg/slf4j/Logger;
    │ +       350: ldc           #245                // String Fatal error: {}
    │         352: aload         16
    │ -       354: invokevirtual #82                 // Method java/lang/Exception.getMessage:()Ljava/lang/String;
    │ +       354: invokevirtual #247                // Method java/lang/Exception.getMessage:()Ljava/lang/String;
    │         357: aload         16
    │ -       359: invokeinterface #83,  4           // InterfaceMethod org/slf4j/Logger.error:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    │ +       359: invokeinterface #248,  4          // InterfaceMethod org/slf4j/Logger.error:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    │         364: iconst_2
    │ -       365: invokestatic  #44                 // Method java/lang/System.exit:(I)V
    │ +       365: invokestatic  #129                // Method java/lang/System.exit:(I)V
    │         368: return
    │        Exception table:
    │           from    to  target type
    │              85    90    93   Class jargs/gnu/CmdLineParser$OptionException
    │             243   342   345   Class java/lang/Exception
    │        LineNumberTable:
    │          line 113: 0
    │ @@ -729,22 +722,22 @@
    │          frame_type = 22 /* same */
    │  
    │    static {};
    │      descriptor: ()V
    │      flags: (0x0008) ACC_STATIC
    │      Code:
    │        stack=1, locals=0, args_size=0
    │ -         0: ldc           #50                 // class com/turn/ttorrent/cli/ClientMain
    │ -         2: invokestatic  #84                 // Method org/slf4j/LoggerFactory.getLogger:(Ljava/lang/Class;)Lorg/slf4j/Logger;
    │ -         5: putstatic     #80                 // Field logger:Lorg/slf4j/Logger;
    │ +         0: ldc           #124                // class com/turn/ttorrent/cli/ClientMain
    │ +         2: invokestatic  #254                // Method org/slf4j/LoggerFactory.getLogger:(Ljava/lang/Class;)Lorg/slf4j/Logger;
    │ +         5: putstatic     #241                // Field logger:Lorg/slf4j/Logger;
    │           8: return
    │        LineNumberTable:
    │          line 43: 0
    │          line 44: 2
    │          line 43: 8
    │  }
    │  SourceFile: "ClientMain.java"
    │  InnerClasses:
    │ -  public static abstract #121= #40 of #27; // OptionException=class jargs/gnu/CmdLineParser$OptionException of class jargs/gnu/CmdLineParser
    │ -  public static abstract #134= #133 of #27; // Option=class jargs/gnu/CmdLineParser$Option of class jargs/gnu/CmdLineParser
    │ -  public static #229= #71 of #61;         // ClientShutdown=class com/turn/ttorrent/client/Client$ClientShutdown of class com/turn/ttorrent/client/Client
    │ -  public static final #312= #234 of #61;  // ClientState=class com/turn/ttorrent/client/Client$ClientState of class com/turn/ttorrent/client/Client
    │ +  public static abstract #312= #306 of #79; // Option=class jargs/gnu/CmdLineParser$Option of class jargs/gnu/CmdLineParser
    │ +  public static abstract #313= #111 of #79; // OptionException=class jargs/gnu/CmdLineParser$OptionException of class jargs/gnu/CmdLineParser
    │ +  public static #314= #213 of #180;       // ClientShutdown=class com/turn/ttorrent/client/Client$ClientShutdown of class com/turn/ttorrent/client/Client
    │ +  public static final #315= #229 of #180; // ClientState=class com/turn/ttorrent/client/Client$ClientState of class com/turn/ttorrent/client/Client

    </pre>
    </details>

   <h4> With gumtree-jvm-bytecode-diff</h4>

    ```
    ===
    update-node
    ---
    majorVersion: 50 [6,8]
    replace 50 by 52 
    ``` 

    The spurious diff contains differences in constant pool entries only.
    However, the decompiled source code is exact same.
    The differences emerge because the same source file is compiled to different target languages.
    Our tool shows exactly that.
